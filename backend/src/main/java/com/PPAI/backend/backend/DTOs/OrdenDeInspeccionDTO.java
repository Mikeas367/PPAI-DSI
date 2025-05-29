package com.PPAI.backend.backend.DTOs;

import java.time.LocalDate;

public class OrdenDeInspeccionDTO {

    private int numeroOrden;
    private LocalDate  fechaHoraFinalizacion;
    private String nombreEstacionSismologica;
    private  int identificadorSismografo;

    public OrdenDeInspeccionDTO(int numeroOrden, LocalDate  fechaHoraFinalizacion, String nombreEstacionSismologica , int identificadorSismografo) {
        this.numeroOrden = numeroOrden;
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
        this.nombreEstacionSismologica = nombreEstacionSismologica;
        this.identificadorSismografo = identificadorSismografo;
    }

    public int getIdentificadorSismografo() {
        return identificadorSismografo;
    }

    public void setIdentificadorSismografo(int identificadorSismografo) {
        this.identificadorSismografo = identificadorSismografo;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public LocalDate getFechaHoraFinalizacion() {
        return fechaHoraFinalizacion;
    }

    public void setFechaHoraFinalizacion(LocalDate  fechaHoraFinalizacion) {
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
    }

    public String getNombreEstacionSismologica() {
        return nombreEstacionSismologica;
    }

    public void setNombreEstacionSismologica(String nombreEstacionSismologica) {
        this.nombreEstacionSismologica = nombreEstacionSismologica;
    }
}
