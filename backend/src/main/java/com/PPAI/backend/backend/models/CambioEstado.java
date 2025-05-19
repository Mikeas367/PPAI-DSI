package com.PPAI.backend.backend.models;

import java.time.LocalDate;

public class CambioEstado {
    private LocalDate fechaHoraFin;
    private LocalDate fechaHoraInicio;

    private Estado estado;

    public boolean esEstadoActual(){
        if(fechaHoraFin == null) {
            return true;
        }
        return false;
    }

    public CambioEstado(LocalDate fechaHoraInicio, Estado estado) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
    }

    public LocalDate getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDate fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public LocalDate getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDate fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
