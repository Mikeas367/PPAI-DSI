/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PPAI.backend.backend.controllers;

import com.PPAI.backend.backend.DTOs.OrdenDeInspeccionDTO;
import com.PPAI.backend.backend.boundarysExternos.InterfazEmail;
import com.PPAI.backend.backend.boundarysExternos.Pantalla;
import com.PPAI.backend.backend.models.CambioEstado;
import com.PPAI.backend.backend.models.Empleado;
import com.PPAI.backend.backend.models.EstacionSismologica;
import com.PPAI.backend.backend.models.Estado;
import com.PPAI.backend.backend.models.MotivoFueraServicio;
import com.PPAI.backend.backend.models.MotivoTipo;
import com.PPAI.backend.backend.models.OrdenDeInspeccion;
import com.PPAI.backend.backend.models.Rol;
import com.PPAI.backend.backend.models.Sesion;
import com.PPAI.backend.backend.models.Sismografo;
import com.PPAI.backend.backend.models.Usuario;
import gui.PantallaCierreDeOrdenDeInspeccion;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Miqueas
 */


public class Gestor {
    
    List<MotivoTipo> motivosTipo = new ArrayList<>();
    List<Sismografo> sismografos = new ArrayList<>();
    List<Empleado> empleados = new ArrayList<>();
    List<Estado> estados = new ArrayList<>();
    List<CambioEstado> cambioEstadosSismografro= new ArrayList<>();

    private InterfazEmail interfazEmail = new InterfazEmail();
    private Sesion sesion;
    private Empleado empleadoLogueado; //esta
    private PantallaCierreDeOrdenDeInspeccion pantalla;

    //ordenesDeInspeccion

    List<OrdenDeInspeccion> ordenesDeInspeccion = new ArrayList<>();
    List<OrdenDeInspeccionDTO> ordenesDeInspeccionFinalizadas = new ArrayList<>();
    List<String> motivoDescripciones = new ArrayList<>();
    
    private Sismografo sismografoSeleccionado; // esta
    private OrdenDeInspeccion ordenSeleccionada; // esta
    private String observacionCierre; // esta

    private MotivoTipo motivoTipoSeleccionado; //esta
    private LocalDate fechaHoraActual; // esta

    private List<MotivoFueraServicio> motivosFueraServicio = new ArrayList<>(); //esta
    List<String> emailEmpleadosRR = new ArrayList<>();
    List<Pantalla> pantallas = new ArrayList<>();

    public void publicarEnLasPantallas(){
        System.out.println("Se actualizan las pantallas");
        for (Pantalla p : pantallas){
            p.actualizarPantalla();
        }

    }

    public void enviarEmails(){
        interfazEmail.enviarMail(emailEmpleadosRR);
    }
    
    public void obtenerFechaHoraActual(){
        this.fechaHoraActual = LocalDate.now();
    }
    public void buscarMailResponsablesReparacion(){
        for (Empleado e : empleados){
            if(e.esResponsableDeReparacion()){
                this.emailEmpleadosRR.add(e.getMail());
            }
        }
    }

    public void validarDatosCierre(){
        if(observacionCierre == null || motivosFueraServicio.isEmpty()){
            System.out.println("ESTAN MAL LOS DATOS");
        } else {
            System.out.println("ESTAN BIEN LOS   DATOS");
        }
    }
    
    public void tomarConfirmacion() {
        validarDatosCierre();
        obtenerFechaHoraActual();
        Estado estadoCerrada = buscarEstadoCerrada();
        this.ordenSeleccionada.cerrar(estadoCerrada, observacionCierre);
        this.ordenSeleccionada.setFechaHoraCierre(fechaHoraActual);
        Estado estadoFueraServicio = buscarEstadoFueraDeServicio();

        this.sismografoSeleccionado.ponerEnFueraServicio(estadoFueraServicio, motivosFueraServicio, fechaHoraActual, empleadoLogueado);
        buscarMailResponsablesReparacion();
        enviarEmails();
        publicarEnLasPantallas();
    }
    
    public void ordenarOrdenesPorFechaDeFinalizacion(){
        this.ordenesDeInspeccionFinalizadas.sort(Comparator.comparing(OrdenDeInspeccionDTO::getFechaHoraFinalizacion));
    }
    public void cerrarOrdenDeInspeccion() {

        buscarEmpleadoLogueado();
        buscarInspeccionesCompletamenteRealizadas();
        ordenarOrdenesPorFechaDeFinalizacion();

        pantalla.mostrarDatosDeOrdenes(ordenesDeInspeccionFinalizadas);
        ordenesDeInspeccionFinalizadas.clear(); // lo limpio porque sino se añaden los mismos datos
        buscarTiposMotivo();
        pantalla.mostrarTiposMotivo(motivosTipo);
    }
    
    // esto es para el motivo de cierre
    public void tomarIngresoComentario(String comentario) {
        MotivoFueraServicio motivo = new MotivoFueraServicio();

        motivo.setComentario(comentario);
        motivo.setMotivoTipo(motivoTipoSeleccionado);

        motivosFueraServicio.add(motivo); // se le mandan para el cambio de estado del sismografo

        System.out.println("Comentario ingresado: " + comentario);
    }

    public void tomarSeleccionDeTipoFueraDeServicio(MotivoTipo motivoTipo) {
        this.motivoTipoSeleccionado = motivoTipo;
        pantalla.solicitarIngresoComentario();
        System.out.println("Motivo seleccionado: " + motivoTipo);
    }
    public void tomarObservacionDeCierre(String txt){
        System.out.println("El texto que envio de la pantalla es: " + txt);
        this.observacionCierre = txt;
    }
    
    public OrdenDeInspeccion buscarOrdenPorId(int id){
        for(OrdenDeInspeccion orden : ordenesDeInspeccion){
            if(orden.getNumeroOrden() == id){
                return orden;
            }
        }
        return null;
    }
    
    public void buscarTiposMotivo(){
        for(MotivoTipo motivo : motivosTipo){
            motivoDescripciones.add(motivo.getDescripcion());
        }
    }
    
    public Estado buscarEstadoCerrada(){
        for(Estado estado: estados){
            if(estado.esDeAmbitoOrdenDeInspeccion() && estado.esCerrada()){
                return estado;
            }
        }
        return null;
    }
    
    public Estado buscarEstadoFueraDeServicio(){
        for(Estado estado: estados){
            if(estado.esDeAmbitoSismografo() && estado.esFueraDeServicio()){
                return estado;
            }
        }
        return null;
    }
    
    public void tomarSeleccionDeOrden(OrdenDeInspeccionDTO ordenInspeccion){
        int idOrden = ordenInspeccion.getNumeroOrden();
        this.ordenSeleccionada = buscarOrdenPorId(idOrden);
        pantalla.solicitarIngresoDeObservacionDeCierre();
        
        
    }

    public void buscarInspeccionesCompletamenteRealizadas(){
        for (OrdenDeInspeccion orden : ordenesDeInspeccion){
            if(orden.esCompletamenteRealizada() && orden.sosDeEmpleado(empleadoLogueado)){
                //ordenesDeInspeccionFinalizadas.add(orden);
                OrdenDeInspeccionDTO dto = orden.obtenerDatos();
                sismografoSeleccionado = dto.getSismografo();
                ordenesDeInspeccionFinalizadas.add(dto);
                
            }
        }
    
    }
    
    public void buscarEmpleadoLogueado(){
        this.empleadoLogueado = sesion.obtenerEmpleado();
    }
    
    public Gestor(PantallaCierreDeOrdenDeInspeccion pantalla){
        this.pantalla = pantalla;
        
        Rol responsableDeReparacion = new Rol("asdad", "Responsable De Reparacion");
        
        
        Rol responsableDeInspeccion = new Rol("ads", "Responsable De Inspeccion");


        Pantalla pantalla1 = new Pantalla(1);
        Pantalla pantalla2 = new Pantalla(2);
        Pantalla pantalla3 = new Pantalla(3);
        pantallas.add(pantalla1);
        pantallas.add(pantalla2);
        pantallas.add(pantalla3);
        
        MotivoTipo motivoTipo = new MotivoTipo("se rompio la punta del sismografo");
        MotivoTipo motivoTipo2 = new MotivoTipo("Se quedo sin hojas");
        MotivoTipo motivoTipo3 = new MotivoTipo("ya dejo de sismosear");
        MotivoTipo motivoTipo4 = new MotivoTipo("me quede sin ideas");

        motivosTipo.add(motivoTipo);
        motivosTipo.add(motivoTipo2);
        motivosTipo.add(motivoTipo3);
        motivosTipo.add(motivoTipo4);

        
        // ACA PONEMOS LOS DATOS ESTATICOS PARA NO HACER UN DB y hacer mas lio,
        // ademas no se si se modelan las nuevas clases que necesita el framework

        // voy a crear un DTO para enviarles los datos al front papu

      

        LocalDate fechaEspecifica1 = LocalDate.of(1900, 1, 1);

        LocalDate fechaEspecifica2 = LocalDate.of(4000, 12, 1);

        LocalDate fechaEspecifica3 = LocalDate.of(2024, 6, 1);

        LocalDate fechaEspecifica4 = LocalDate.of(2023, 9, 13);

        LocalDate fechaEspecifica5 = LocalDate.of(2022, 9, 25);

        LocalDate fechaEspecifica6 = LocalDate.of(1999, 9, 6);

        // estaciones sismologicas
        EstacionSismologica estacionSismologica1 = new EstacionSismologica(123, null, null, 5, 3, "Cordoba", 1231);
        EstacionSismologica estacionSismologica2 = new EstacionSismologica(124, null, null, 9, 7, "Rio Tercero", 1352);
        EstacionSismologica estacionSismologica3 = new EstacionSismologica(125, null, null, 1, 1, "AlmaFuerte", 1272);
        EstacionSismologica estacionSismologica4 = new EstacionSismologica(126, null, null, 7, 5, "Rio Cuarto", 1251);
        EstacionSismologica estacionSismologica5 = new EstacionSismologica(127, null, null, 1, 8, "La Pampa", 1266);
        EstacionSismologica estacionSismologica6 = new EstacionSismologica(128, null, null, 8, 0, "La Rioja", 1222);

        Sismografo sismografo1 = new Sismografo(fechaEspecifica1, 111, "PH01L98", estacionSismologica1, null, null);
        Sismografo sismografo2 = new Sismografo(fechaEspecifica2, 222, "PH00F98", estacionSismologica2, null, null);
        Sismografo sismografo3 = new Sismografo(fechaEspecifica3, 333, "PH05P98", estacionSismologica3, null, null);
        Sismografo sismografo4 = new Sismografo(fechaEspecifica4, 444, "PH02M98", estacionSismologica4, null, null);
        Sismografo sismografo5 = new Sismografo(fechaEspecifica5, 555, "PH08W98", estacionSismologica5, null, null);
        Sismografo sismografo6 = new Sismografo(fechaEspecifica6, 666, "PH03X98", estacionSismologica6, null, null);

        sismografoSeleccionado = sismografo1;

        sismografos.add(sismografo1);
        sismografos.add(sismografo2);
        sismografos.add(sismografo3);
        sismografos.add(sismografo4);
        sismografos.add(sismografo5);
        sismografos.add(sismografo6);

        estacionSismologica1.setSismografos(sismografos);
        estacionSismologica2.setSismografos(sismografos);
        estacionSismologica3.setSismografos(sismografos);
        estacionSismologica4.setSismografos(sismografos);
        estacionSismologica5.setSismografos(sismografos);
        estacionSismologica6.setSismografos(sismografos);

        // creacion empleados
        Empleado empleado1 = new Empleado("Lopez", "dieguito10@gmail.com", "Diego", "1010", responsableDeInspeccion);
        Empleado empleado2 = new Empleado("Gariglio", "juanceto01@gmail.com", "Juan", "1333", responsableDeInspeccion);
        Empleado empleado3 = new Empleado("Estevez", "kris12@gmail.com", "Miguel", "9243", responsableDeInspeccion);
        
        Empleado empleado4 = new Empleado("Puro", "purohueso@gmail.com", "Hueso", "1010", responsableDeReparacion);
        Empleado empleado5 = new Empleado("Billy", "byllymandy@gmail.com", "Mandy", "1333", responsableDeReparacion);
        Empleado empleado6 = new Empleado("Pepito", "perezpepito@gmail.com", "perez", "9243", responsableDeReparacion);
   
        empleados.add(empleado1);
        empleados.add(empleado2);
        empleados.add(empleado3);
        empleados.add(empleado4);
        empleados.add(empleado5);
        empleados.add(empleado6);


        // creacion estados

        Estado estadoCompletamenteRealizada = new Estado("Ambito Orden", "Completamente Realizada");
        Estado estadoPendienteDeRealizacion = new Estado("Ambito Orden","Pendiente De Realizacion");
        Estado estadoCerrada = new Estado("Ambito Orden","Cerrada");
        
        Estado estadoFueraServicio = new Estado("Ambito Sismografo", "Fuera De Servicio");
        Estado estadoEnLinea = new Estado("Ambito Sismografo", "En Linea");
        Estado estadoFueraDeLinea = new Estado("Ambito Sismografo", "Fuera De Linea");



        //  cerrada


        OrdenDeInspeccion ordenDeInspeccion = new OrdenDeInspeccion(null, fechaEspecifica1, null, 123, "");
        ordenDeInspeccion.setEmpleado(empleado1);
        ordenDeInspeccion.setEstado(estadoPendienteDeRealizacion);
        ordenDeInspeccion.setEstacionSismologica(estacionSismologica5);

        OrdenDeInspeccion ordenDeInspeccion2 = new OrdenDeInspeccion(null, fechaEspecifica2, null, 456, "");
        ordenDeInspeccion2.setEmpleado(empleado1);
        ordenDeInspeccion2.setEstado(estadoPendienteDeRealizacion);
        ordenDeInspeccion2.setEstacionSismologica(estacionSismologica2);

        OrdenDeInspeccion ordenDeInspeccion3 = new OrdenDeInspeccion(null, fechaEspecifica3, null, 789, "");
        ordenDeInspeccion3.setEmpleado(empleado2);
        ordenDeInspeccion3.setEstado(estadoCompletamenteRealizada);
        ordenDeInspeccion3.setEstacionSismologica(estacionSismologica4);

        OrdenDeInspeccion ordenDeInspeccion4 = new OrdenDeInspeccion(null, fechaEspecifica4, null, 101, "");
        ordenDeInspeccion4.setEmpleado(empleado2);
        ordenDeInspeccion4.setEstado(estadoCompletamenteRealizada);
        ordenDeInspeccion4.setEstacionSismologica(estacionSismologica3);

        OrdenDeInspeccion ordenDeInspeccion5 = new OrdenDeInspeccion(null, fechaEspecifica5, null, 121, "");
        ordenDeInspeccion5.setEmpleado(empleado3);
        ordenDeInspeccion5.setEstado(estadoCompletamenteRealizada);
        ordenDeInspeccion5.setEstacionSismologica(estacionSismologica1);

        OrdenDeInspeccion ordenDeInspeccion6 = new OrdenDeInspeccion(null, fechaEspecifica6, null, 141, "");
        ordenDeInspeccion6.setEmpleado(empleado3);
        ordenDeInspeccion6.setEstado(estadoCompletamenteRealizada);
        ordenDeInspeccion6.setEstacionSismologica(estacionSismologica6);

        ordenesDeInspeccion.add(ordenDeInspeccion);
        ordenesDeInspeccion.add(ordenDeInspeccion2);
        ordenesDeInspeccion.add(ordenDeInspeccion3);
        ordenesDeInspeccion.add(ordenDeInspeccion4);
        ordenesDeInspeccion.add(ordenDeInspeccion5);
        ordenesDeInspeccion.add(ordenDeInspeccion6);

        // creacion de usuario y sesion
        Usuario usuario1 = new Usuario("juanceto01", "1234", empleado1);
        Sesion sesion1 = new Sesion(usuario1);

        Usuario usuario2 = new Usuario("kris12", "9934", empleado2);
        Sesion sesion2 = new Sesion(usuario2);

        Usuario usuario3 = new Usuario("DiegoyoloARG", "1299", empleado3);
        Sesion sesion3 = new Sesion(usuario3);

        this.sesion = sesion3; // aca podemos elegir la sesion, LA SESION 1 NO TIENE ORDENES COMPLETADAS
        // (me olvide q se lo habia puesto asi y estuve renegando como mono porque no sabia q re poronga era q me enviava un array vacio)

        //buscarEmpleadoLogueado();
        //buscarInspeccionesCompletamenteFinalizadas(); // ojo con sacar esta linea porque esta ejecuta el metodo que busca el sismografo xd -> hay que ver como podemos hacer para obtener el sismografo si utilizar este metodo
        //ordenesDeInspeccionFinalizadas.sort(Comparator.comparing(OrdenDeInspeccion::getFechaHoraFinalizacion));

        // aca es medio un lio porque necesitaba que el sismografro tuviese cambio de estados.
        sismografoSeleccionado.setEstadoActual(estadoFueraDeLinea);
        CambioEstado  cambioEstado1 = new CambioEstado(fechaEspecifica1, estadoEnLinea, null);
        cambioEstado1.setFechaHoraFin(LocalDate.now());

        CambioEstado cambioEstado2 = new CambioEstado(fechaEspecifica2, estadoFueraDeLinea, null);
        cambioEstadosSismografro.add(cambioEstado1);
        cambioEstadosSismografro.add(cambioEstado2);// este tiene fecha fin null
        cambioEstado2.setFechaHoraFin(null);
        sismografoSeleccionado.setCambioEstados(cambioEstadosSismografro);
        sismografo6.setCambioEstados(cambioEstadosSismografro);
        sismografo1.setCambioEstados(cambioEstadosSismografro);

        estados.add(estadoCompletamenteRealizada);
        estados.add(estadoPendienteDeRealizacion);
        estados.add(estadoCerrada);
        estados.add(estadoFueraServicio);
        estados.add(estadoEnLinea);
    }

}
