package com.PPAI.backend.backend.models;

import java.time.LocalDate;
import java.util.List;

public class CambioEstado {
    private LocalDate fechaHoraFin;
    private LocalDate fechaHoraInicio;

    private Estado estado;
    private List<MotivoFueraServicio> motivosFueraServicio;
    private Empleado responsableDeInspeccion;

    public void MostrarMotivos(){
        for (MotivoFueraServicio m : motivosFueraServicio){
            System.out.println("Esto es la motivo" + m.getComentario());
        }
    }

    public List<MotivoFueraServicio> getMotivoFueraServicio() {
        return motivosFueraServicio;
    }

    public void setMotivosFueraServicio(List<MotivoFueraServicio> motivosFueraServicio) {
        this.motivosFueraServicio = motivosFueraServicio;
    }

    public boolean esActual(){
        if(fechaHoraFin == null) {
            return true;
        }
        return false;
    }

    public CambioEstado(LocalDate fechaHoraInicio, Estado estado, Empleado responsableDeInspeccion) {
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
