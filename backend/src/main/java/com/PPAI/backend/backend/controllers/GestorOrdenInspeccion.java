package com.PPAI.backend.backend.controllers;

import com.PPAI.backend.backend.DTOs.OrdenDeInspeccionDTO;
import com.PPAI.backend.backend.models.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private  List<OrdenDeInspeccionDTO> inspeccionesCompletamenteFinalizadas = new ArrayList<>(); // son las que encuentro con el empleado

    @GetMapping("/finalizadas")
    public List<OrdenDeInspeccionDTO> obtenerFinalizadasPorEmpleado() {
        return inspeccionesCompletamenteFinalizadas;
    }

    public Empleado buscarEmpleadoLogueado(){
        this.empleadoLogueado = sesion.getUsuario().getEmpleado();
        return sesion.getUsuario().getEmpleado();
    }




    public void buscarInspeccionesCompletamenteFinalizadas(){
        System.out.println("aca entro wacho");

        for (OrdenDeInspeccion orden: ordenesDeInspeccion) {
            System.out.println("si no leo esto soy gey");
            System.out.println(orden.getNumeroOrden());
            if (orden.sosDeEmpleado(empleadoLogueado) && orden.esCompletamenteRealizada()){
                OrdenDeInspeccionDTO ordenDTO = orden.obtenerDatos();
                inspeccionesCompletamenteFinalizadas.add(ordenDTO);
            }
        }
    }


    // constructor
    public GestorOrdenInspeccion() {

        // ACA PONEMOS LOS DATOS ESTATICOS PARA NO HACER UN DB y hacer mas lio,
        // ademas no se si se modelan las nuevas clases que necesita el framework

        // voy a crear un DTO para enviarles los datos al front papu

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
        Empleado empleado1 = new Empleado("Lopez", "dieguito10@gmail.com", "Diego", "123");
        Empleado empleado2 = new Empleado("Gariglio", "juanceto01@gmail.com", "Juan", "133");
        Empleado empleado3 = new Empleado("Estevez", "kris12@gmail.com", "Miguel", "923");


        // creacion estados

        Estado estadoCompletamenteRealizada = new Estado();
        estadoCompletamenteRealizada.setNombreEstado("Completamente Realizada");


        Estado estadoPendienteDeRealizacion = new Estado();
        estadoPendienteDeRealizacion.setNombreEstado("Pendiente De Realizacion");

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
        inspeccionesCompletamenteFinalizadas.sort(Comparator.comparing(OrdenDeInspeccionDTO::getFechaHoraFinalizacion));

        //inspeccionesCompletamenteFinalizadas
    }


    public GestorOrdenInspeccion(Sesion sesion) {
        this.sesion = sesion;
    }

}
