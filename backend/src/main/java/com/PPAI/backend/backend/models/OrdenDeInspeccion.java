package com.PPAI.backend.backend.models;

import com.PPAI.backend.backend.DTOs.OrdenDeInspeccionDTO;

import java.time.LocalDate;
import java.util.List;

public class OrdenDeInspeccion {
    private LocalDate  fechaHoraCierre;
    private LocalDate  fechaHoraFinalizacion;
    private LocalDate fechaHoraInicio;
    private int numeroOrden;
    private String observacionCierre;

    // atributos referenciales
    private Estado estado;
    private EstacionSismologica estacionSismologica;
    private Empleado empleado;


    // metodos de la solucion

    public void cerrar(Estado estado, String observacionCierre){
        System.out.println("Se Cerro la Orden por: " + observacionCierre);
        this.estado = estado;
        this.observacionCierre = observacionCierre;
        
    }

    public OrdenDeInspeccionDTO obtenerDatos() {
        Sismografo sis = estacionSismologica.buscarSismografo();
        OrdenDeInspeccionDTO datos = new OrdenDeInspeccionDTO(this.numeroOrden, this.fechaHoraFinalizacion,
                this.estacionSismologica.getNombre(), sis.getIdentificadorSismografo());
        return datos;
    }

    public boolean sosDeEmpleado(Empleado empleado) {
        if(this.empleado.equals(empleado)) {
            return true;
        }
        return false;
    }

    public boolean esCompletamenteRealizada() {
        if (this.estado.sosCompletamenteRealizada()){
            return true;
        }
        return false;
    }

    // constructores

    public OrdenDeInspeccion(LocalDate  fechaHoraCierre, LocalDate  fechaHoraFinalizacion,
                             LocalDate  fechaHoraInicio, int numeroOrden, String observacionCierre) {
        this.fechaHoraCierre = fechaHoraCierre;
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
        this.fechaHoraInicio = fechaHoraInicio;
        this.numeroOrden = numeroOrden;
        this.observacionCierre = observacionCierre;
    }

    // getters and setters
    public LocalDate  getFechaHoraCierre() {
        return fechaHoraCierre;
    }

    public void setFechaHoraCierre(LocalDate  fechaHoraCierre) {
        this.fechaHoraCierre = fechaHoraCierre;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaHoraFinalizacion;
    }

    public void setFechaHoraFinalizacion(LocalDate fechaHoraFinalizacion) {
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
    }

    public LocalDate  getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDate  fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getObservacionCierre() {
        return observacionCierre;
    }

    public void setObservacionCierre(String observacionCierre) {
        this.observacionCierre = observacionCierre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public EstacionSismologica getEstacionSismologica() {
        return estacionSismologica;
    }

    public void setEstacionSismologica(EstacionSismologica estacionSismologica) {
        this.estacionSismologica = estacionSismologica;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

}
