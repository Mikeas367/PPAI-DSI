package com.PPAI.backend.backend.DTOs;

import com.PPAI.backend.backend.models.Sismografo;

import java.time.LocalDate;

public class OrdenDeInspeccionDTO {

    private int numeroOrden;
    private LocalDate  fechaHoraFinalizacion;
    private String nombreEstacionSismologica;
    private  int identificadorSismografo;
    private Sismografo sismografo;

    public OrdenDeInspeccionDTO(int numeroOrden, LocalDate  fechaHoraFinalizacion, String nombreEstacionSismologica , int identificadorSismografo, Sismografo sismografo) {
        this.sismografo = sismografo;
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

    public Sismografo getSismografo() {
        return sismografo;
    }

    public void setSismografo(Sismografo sismografo) {
        this.sismografo = sismografo;
    }

    public String getNombreEstacionSismologica() {
        return nombreEstacionSismologica;
    }

    public void setNombreEstacionSismologica(String nombreEstacionSismologica) {
        this.nombreEstacionSismologica = nombreEstacionSismologica;
    }
}
