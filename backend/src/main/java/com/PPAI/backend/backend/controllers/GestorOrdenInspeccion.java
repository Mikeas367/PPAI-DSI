package com.PPAI.backend.backend.controllers;

import com.PPAI.backend.backend.DTOs.OrdenDeInspeccionDTO;
import com.PPAI.backend.backend.models.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GestorOrdenInspeccion {
    private  List<OrdenDeInspeccion> ordenesDeInspeccion = new ArrayList<>(); // estas serian las que estan en la base de datos
    private Sesion sesion;
    private Empleado empleadoLogueado; // este es el empleado que busco de la sesion
    private  List<OrdenDeInspeccion> ordenesDeInspeccionFinalizadas = new ArrayList<>();
    List<MotivoTipo> motivoTipos = new ArrayList<>();
    List<Estado> estados = new ArrayList<>();

    @PutMapping("/ordenes/{nroOrden}/estado")
    public ResponseEntity actualizarEstado(@PathVariable int nroOrden, @RequestBody Estado estado) {
        System.out.println("ME LLEGO EL PING" + nroOrden);
        System.out.println(ordenesDeInspeccion);

        for (OrdenDeInspeccion orden : ordenesDeInspeccion) {
            System.out.println("Esta es la orden" + orden.getNumeroOrden());
            if(orden.getNumeroOrden() == nroOrden){
                orden.setEstado(estado);
                System.out.println("EL ESTADO SE CAMBIO A: " + orden.getEstado().getNombreEstado());
                return ResponseEntity.ok("Estado actualizado con éxito");
            }

        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Orden no encontrada");
    }


    @GetMapping("/finalizadas")
    public List<OrdenDeInspeccionDTO> buscarInspeccionesCompletamenteFinalizadas() {
        List<OrdenDeInspeccionDTO> dtos = new ArrayList<>();
        for (OrdenDeInspeccion orden : ordenesDeInspeccion) {
            if(orden.sosDeEmpleado(empleadoLogueado) && orden.esCompletamenteRealizada()){
                OrdenDeInspeccionDTO dto = orden.obtenerDatos();
                dtos.add(dto);
            }
        }
        return dtos;
    }

    @GetMapping("/todas")
    public List<OrdenDeInspeccion> obtenerTodas() {
        return ordenesDeInspeccion;
    }

    @GetMapping("/motivos-tipo")
    public List<MotivoTipo> obtenerMotivoTipos() {
        return motivoTipos;
    }

    public Empleado buscarEmpleadoLogueado(){
        this.empleadoLogueado = sesion.getUsuario().getEmpleado();
        return sesion.getUsuario().getEmpleado();
    }

    public void buscarTiposParaFueraDeServicio() {
        for (MotivoTipo motivoTipo : motivoTipos) {
            System.out.println(motivoTipo.getDescripcion());
        }
    }

    public Estado buscarEstadoCerrado() {
        for (Estado estado : estados) {
            if (estado.esDeAmbitoOrdenDeInspeccion() && estado.esCerrada()){
                return estado;
            }
        }
        return null;
    }



    //public void buscarInspeccionesCompletamenteFinalizadas(){
    //    System.out.println("aca entro wacho");
    //
    //    for (OrdenDeInspeccion orden: ordenesDeInspeccion) {
    //        if (orden.sosDeEmpleado(empleadoLogueado) && orden.esCompletamenteRealizada()){
    //            ordenesDeInspeccionFinalizadas.add(orden);
    //        }
    //    }
    //}


    // constructor
    public GestorOrdenInspeccion() {

        // ACA PONEMOS LOS DATOS ESTATICOS PARA NO HACER UN DB y hacer mas lio,
        // ademas no se si se modelan las nuevas clases que necesita el framework

        // voy a crear un DTO para enviarles los datos al front papu

        MotivoTipo motivoTipo = new MotivoTipo("se rompio la punta del sismografo");
        MotivoTipo motivoTipo2 = new MotivoTipo("Se quedo sin hojas");
        MotivoTipo motivoTipo3 = new MotivoTipo("ya dejo de sismosear");
        MotivoTipo motivoTipo4 = new MotivoTipo("me quede sin ideas");

        motivoTipos.add(motivoTipo);
        motivoTipos.add(motivoTipo2);
        motivoTipos.add(motivoTipo3);
        motivoTipos.add(motivoTipo4);

        LocalDate fechaEspecifica1 = LocalDate.of(2026, 1, 1);

        LocalDate fechaEspecifica2 = LocalDate.of(2025, 12, 1);

        LocalDate fechaEspecifica3 = LocalDate.of(2025, 6, 1);

        LocalDate fechaEspecifica4 = LocalDate.of(2025, 9, 13);

        LocalDate fechaEspecifica5 = LocalDate.of(2025, 9, 25);

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

        List<Sismografo> sismografos = new ArrayList<>();
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
        Empleado empleado1 = new Empleado("Lopez", "dieguito10@gmail.com", "Diego", "1010");
        Empleado empleado2 = new Empleado("Gariglio", "juanceto01@gmail.com", "Juan", "1333");
        Empleado empleado3 = new Empleado("Estevez", "kris12@gmail.com", "Miguel", "9243");


        // creacion estados

        Estado estadoCompletamenteRealizada = new Estado("Ambito Orden", "Completamente Realizada");
        Estado estadoPendienteDeRealizacion = new Estado("Ambito Orden","Pendiente De Realizacion");
        Estado estadoCerrada = new Estado("Ambito Orden","Cerrada");

        estados.add(estadoCompletamenteRealizada);
        estados.add(estadoPendienteDeRealizacion);
        estados.add(estadoCerrada);

        //  cerrada


        OrdenDeInspeccion ordenDeInspeccion = new OrdenDeInspeccion(null, fechaEspecifica1, null, 123, "caca");
        ordenDeInspeccion.setEmpleado(empleado1);
        ordenDeInspeccion.setEstado(estadoPendienteDeRealizacion);
        ordenDeInspeccion.setEstacionSismologica(estacionSismologica5);

        OrdenDeInspeccion ordenDeInspeccion2 = new OrdenDeInspeccion(null, fechaEspecifica2, null, 456, "caca2");
        ordenDeInspeccion2.setEmpleado(empleado1);
        ordenDeInspeccion2.setEstado(estadoPendienteDeRealizacion);
        ordenDeInspeccion2.setEstacionSismologica(estacionSismologica2);

        OrdenDeInspeccion ordenDeInspeccion3 = new OrdenDeInspeccion(null, fechaEspecifica3, null, 789, "caca3");
        ordenDeInspeccion3.setEmpleado(empleado2);
        ordenDeInspeccion3.setEstado(estadoCompletamenteRealizada);
        ordenDeInspeccion3.setEstacionSismologica(estacionSismologica4);

        OrdenDeInspeccion ordenDeInspeccion4 = new OrdenDeInspeccion(null, fechaEspecifica4, null, 101, "caca4");
        ordenDeInspeccion4.setEmpleado(empleado2);
        ordenDeInspeccion4.setEstado(estadoCompletamenteRealizada);
        ordenDeInspeccion4.setEstacionSismologica(estacionSismologica3);

        OrdenDeInspeccion ordenDeInspeccion5 = new OrdenDeInspeccion(null, fechaEspecifica5, null, 121, "caca5");
        ordenDeInspeccion5.setEmpleado(empleado3);
        ordenDeInspeccion5.setEstado(estadoCompletamenteRealizada);
        ordenDeInspeccion5.setEstacionSismologica(estacionSismologica1);

        OrdenDeInspeccion ordenDeInspeccion6 = new OrdenDeInspeccion(null, fechaEspecifica6, null, 141, "caca6");
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

        buscarEmpleadoLogueado();
        buscarInspeccionesCompletamenteFinalizadas();
        ordenesDeInspeccionFinalizadas.sort(Comparator.comparing(OrdenDeInspeccion::getFechaHoraFinalizacion));
        buscarTiposParaFueraDeServicio();

        //inspeccionesCompletamenteFinalizadas
    }


    public GestorOrdenInspeccion(Sesion sesion) {
        this.sesion = sesion;
    }

}
