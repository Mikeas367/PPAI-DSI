package com.PPAI.backend.backend.controllers;

import com.PPAI.backend.backend.DTOs.OrdenDeInspeccionDTO;
import com.PPAI.backend.backend.boundarysExternos.InterfazEmail;
import com.PPAI.backend.backend.config.ConexionJPA;
import com.PPAI.backend.backend.interfaces.IObservador;
import com.PPAI.backend.backend.interfaces.Isujeto;
import com.PPAI.backend.backend.models.*;
import com.PPAI.backend.backend.repositories.*;
import gui.PantallaCierreDeOrdenDeInspeccion;
import gui.PantallasCCRS.Pantalla;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Miqueas
 */


public class Gestor implements Isujeto {



    List<MotivoTipo> motivosTipo = new ArrayList<>();
    List<Sismografo> sismografos = new ArrayList<>();
    List<Empleado> empleados = new ArrayList<>();
    List<Estado> estados = new ArrayList<>();
    List<CambioEstado> cambioEstadosSismografro= new ArrayList<>();

    private Sesion sesion;
    private Empleado empleadoLogueado; //esta
    private PantallaCierreDeOrdenDeInspeccion pantalla;
    private List<Pantalla> pantallasCCRS1 = new ArrayList<>();

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
    private List<String> mailsResponsablesReparacion = new ArrayList<>();


    List<IObservador> observers = new ArrayList<>();

    @Override
    public void registrarObservador(IObservador observador) {
        observers.add(observador);
    }

    @Override
    public void eliminarObservador(IObservador observador) {


    }

    @Override
    public void notificar() {
        int identificadorSismografo = sismografoSeleccionado.getIdentificadorSismografo();
        String estadoActual = sismografoSeleccionado.getEstadoActual().getNombreEstado();
        String mensaje = crearMensaje(identificadorSismografo, estadoActual);
        for(IObservador observador : observers) {
            observador.actualizar(identificadorSismografo, estadoActual,fechaHoraActual, mensaje);
        }
    }

    public String crearMensaje(int id_sismografo, String estado) {
        StringBuilder cuerpo = new StringBuilder();
        cuerpo.append("El sismógrafo ").append(id_sismografo)
                .append(" cambió a estado ").append(estado).append("\n\nMotivos:\n");

        for (MotivoFueraServicio m : motivosFueraServicio) {
            cuerpo.append(" - ").append(m.getMotivoTipo().getDescripcion()).append("\n");
            cuerpo.append("    ").append(m.getComentario()).append("\n");
        }
        return cuerpo.toString();
    }

    public InterfazEmail crearInterfaceEmail(){
        return new InterfazEmail(mailsResponsablesReparacion);
    }

    public void notificarPantallasyResponsables() {
        buscarMailResponsablesReparacion();
        InterfazEmail interfazEmail = crearInterfaceEmail();

        for(Pantalla pantalla : pantallasCCRS1){
            registrarObservador(pantalla);
        }
        registrarObservador(interfazEmail);

        notificar();
    }



    public void obtenerFechaHoraActual(){
        this.fechaHoraActual = LocalDate.now();
    }

    public void buscarMailResponsablesReparacion(){
        for (Empleado e : empleados){
            if(e.esResponsableDeReparacion()){
                mailsResponsablesReparacion.add(e.getMail());
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
        System.out.printf("ESTADO FUERA DE SERVICIO: %s\n", estadoFueraServicio);

        this.sismografoSeleccionado.ponerEnFueraServicio(estadoFueraServicio, motivosFueraServicio, fechaHoraActual, empleadoLogueado);
        notificarPantallasyResponsables();
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
            System.out.printf("Estados: %s\n",estado.getNombreEstado());
            if(estado.esDeAmbitoSismografo() && estado.esFueraDeServicio()){
                System.out.printf("ENCONTRO EL ESTADO: " + estado.getNombreEstado());
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
                OrdenDeInspeccionDTO dto = orden.obtenerDatos(sismografos);
                sismografoSeleccionado = dto.getSismografo();
                System.out.println("Sismografo seleccionado: " + sismografoSeleccionado.getNroSerie() + "Estado Actual:" + sismografoSeleccionado.getEstadoActual().getNombreEstado());
                ordenesDeInspeccionFinalizadas.add(dto);
                
            }
        }
    
    }
    
    public void buscarEmpleadoLogueado(){
        this.empleadoLogueado = sesion.obtenerEmpleado();
    }

    private final SismografoRepository repoSismografo;
    private final EmpleadoRepository repoEmpleado;
    private final RolRepository repoRol;
    private final MotivoTipoRepository repomotivotipo;
    private final EstadoRepository repoestado;
    private final  EstacionSismologicaRepository repoestacion;
    private final  CambioEstadoRepository repocambioEstado;
    private final  OrdenDeInspeccionRepository repocOrdenDeInspeccion;

    public Gestor(){
        //this.pantalla = pantalla;
        // el entity manager no deberia estar aca, pero bueno funciona

        Pantalla pantallaCCRS1 = new Pantalla(1);
        Pantalla pantallaCCRS2 = new Pantalla(2);
        Pantalla pantallaCCRS3 = new Pantalla(3);
        pantallaCCRS1.setVisible(true);
        pantallaCCRS2.setVisible(true);
        pantallaCCRS3.setVisible(true);
        pantallasCCRS1.add(pantallaCCRS1);
        pantallasCCRS1.add(pantallaCCRS2);
        pantallasCCRS1.add(pantallaCCRS3);



        EntityManager em = ConexionJPA.getEntityManager();
        this.repoSismografo = new SismografoRepository(em);
        this.repoEmpleado = new EmpleadoRepository(em);
        this.repoRol = new RolRepository(em);
        this.repomotivotipo = new MotivoTipoRepository(em);
        this.repoestado = new EstadoRepository(em);
        this.repoestacion = new EstacionSismologicaRepository(em);
        this.repocambioEstado = new CambioEstadoRepository(em);
        this.repocOrdenDeInspeccion = new OrdenDeInspeccionRepository(em);

        // creo los objetos para luego perisistirlos. ¿Como voy a hacer con los cambios de estado, con esas tablas intermedias?
        Rol inspector = new Rol("Responsable de la inspeccion de sismografos", "Inspector");
        Rol RespRep = new Rol("Responsable de la repacion de sismografos", "Responsable De Reparacion");

        repoRol.crear(inspector);
        repoRol.crear(RespRep);

        MotivoTipo motivoTipo = new MotivoTipo("Averia por vibracion");
        MotivoTipo motivoTipo2 = new MotivoTipo("Desgaste de componente");
        MotivoTipo motivoTipo3 = new MotivoTipo("Fallo en el sistema de registro");
        MotivoTipo motivoTipo4 = new MotivoTipo("Fallo en fuente de alimentación");

        repomotivotipo.crear(motivoTipo);
        repomotivotipo.crear(motivoTipo2);
        repomotivotipo.crear(motivoTipo3);
        repomotivotipo.crear(motivoTipo4);

        Estado enlinea = new Estado("Ambito Sismografo", "En Linea");
        Estado fueraServico = new Estado("Ambito Sismografo", "Fuera De Servicio");
        Estado compleatamente = new Estado("Ambito Orden", "Completamente Realizada");
        Estado cerrada = new Estado("Ambito Orden", "Cerrada");
        repoestado.crear(enlinea);
        repoestado.crear(fueraServico);
        repoestado.crear(compleatamente);
        repoestado.crear(cerrada);

        // creacion empleados
        Empleado empleado1 = new Empleado("Lopez", "dustinferno60@gmail.com", "Diego", "1010", RespRep);
        Empleado empleado2 = new Empleado("Gariglio", "miqueasenry@gmail.com", "Juan", "1333", RespRep);
        //Empleado empleado5 = new Empleado("JOSEFU", "miqueasenry@gmail.com", "PEpe", "1333", RespRep);
        Empleado empleado3 = new Empleado("Estevez", "miguelEsteve@gmail.com", "Miguel", "9243", inspector);
        repoEmpleado.crear(empleado1);
        repoEmpleado.crear(empleado2);
        repoEmpleado.crear(empleado3);
        //repoEmpleado.crear(empleado5);

        EstacionSismologica estacionSismologica1 = new EstacionSismologica(123, null, null, 5, 3, "Cordoba", 1231);
        EstacionSismologica estacionSismologica2 = new EstacionSismologica(124, null, null, 9, 7, "Rio Tercero", 1352);
        EstacionSismologica estacionSismologica3 = new EstacionSismologica(125, null, null, 1, 1, "AlmaFuerte", 1272);
        EstacionSismologica estacionSismologica4 = new EstacionSismologica(126, null, null, 7, 5, "Rio Cuarto", 1251);
        EstacionSismologica estacionSismologica5 = new EstacionSismologica(127, null, null, 1, 8, "La Pampa", 1266);
        EstacionSismologica estacionSismologica6 = new EstacionSismologica(128, null, null, 8, 0, "La Rioja", 1222);

        repoestacion.crear(estacionSismologica1);
        repoestacion.crear(estacionSismologica2);
        repoestacion.crear(estacionSismologica3);
        repoestacion.crear(estacionSismologica4);
        repoestacion.crear(estacionSismologica5);
        repoestacion.crear(estacionSismologica6);

        LocalDate fechaEspecifica1 = LocalDate.of(1900, 1, 1);
        LocalDate fechaEspecifica2 = LocalDate.of(4000, 12, 1);
        LocalDate fechaEspecifica3 = LocalDate.of(2024, 6, 1);
        LocalDate fechaEspecifica4 = LocalDate.of(2023, 9, 13);
        LocalDate fechaEspecifica5 = LocalDate.of(2022, 9, 25);
        LocalDate fechaEspecifica6 = LocalDate.of(1999, 9, 6);

        Sismografo sismografo1 = new Sismografo(fechaEspecifica1, 111, "PH01L98", estacionSismologica5, null, null);
        Sismografo sismografo2 = new Sismografo(fechaEspecifica2, 222, "PH00F98", estacionSismologica2, null, null);
        Sismografo sismografo3 = new Sismografo(fechaEspecifica3, 333, "PH05P98", estacionSismologica3, null, null);
        Sismografo sismografo4 = new Sismografo(fechaEspecifica4, 444, "PH02M98", estacionSismologica4, null, null);
        Sismografo sismografo5 = new Sismografo(fechaEspecifica5, 555, "PH08W98", estacionSismologica5, null, null);
        Sismografo sismografo6 = new Sismografo(fechaEspecifica6, 666, "PH03X98", estacionSismologica6, null, null);


        CambioEstado  cambioEstado1 = new CambioEstado(fechaEspecifica1, fueraServico, null);
        cambioEstado1.setFechaHoraFin(LocalDate.now());

        CambioEstado cambioEstado2 = new CambioEstado(fechaEspecifica2, enlinea, null);
        cambioEstadosSismografro.add(cambioEstado1);
        cambioEstadosSismografro.add(cambioEstado2);// este tiene fecha fin null
        cambioEstado2.setFechaHoraFin(null);

        sismografo1.setCambioEstados(cambioEstadosSismografro);

        repoSismografo.crear(sismografo1);

        cambioEstado1.setSismografo(sismografo1);
        cambioEstado2.setSismografo(sismografo1);
        sismografo1.setEstadoActual(enlinea);
        repocambioEstado.crear(cambioEstado1);
        repocambioEstado.crear(cambioEstado2);

        this.sismografos = repoSismografo.obtenerTodos();

        repoEmpleado.obtenerPorMail("miqueasenry@gmail.com");
        Usuario usuario1 = new Usuario("juanceto01", "1234", empleado1);
        Sesion sesion1 = new Sesion(usuario1);
        this.sesion = sesion1;


        //repoSismografo.crear(sismografo2);
        //repoSismografo.crear(sismografo3);
        //repoSismografo.crear(sismografo4);

        OrdenDeInspeccion ordenDeInspeccion = new OrdenDeInspeccion(null, fechaEspecifica1, null, 123, "");
        ordenDeInspeccion.setEmpleado(empleado1);
        ordenDeInspeccion.setEstado(compleatamente);
        ordenDeInspeccion.setEstacionSismologica(estacionSismologica5);

        repocOrdenDeInspeccion.crear(ordenDeInspeccion);

        this.ordenesDeInspeccion = repocOrdenDeInspeccion.obtenerTodos();

        for(Sismografo s: sismografos){
            System.out.println(s.getNroSerie());
            System.out.println(s.getEstadoActual().getNombreEstado());
        }

        motivosTipo = repomotivotipo.obtenerTodos();
        estados = repoestado.obtenerTodos();
        empleados = repoEmpleado.obtenerTodos();






    }


    public void setPantalla(PantallaCierreDeOrdenDeInspeccion pantallaCierreDeOrdenDeInspeccion) {
        this.pantalla = pantallaCierreDeOrdenDeInspeccion;
    }
}
