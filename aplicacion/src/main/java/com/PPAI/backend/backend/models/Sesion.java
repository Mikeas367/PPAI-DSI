package com.PPAI.backend.backend.models;

import java.time.LocalDate;

public class Sesion {

    private Usuario usuario;
    private LocalDate fechaHoraInicio;
    private LocalDate fechaHoraFin;

    public Empleado obtenerEmpleado(){
        return usuario.getRILogueado();
    }


    public Sesion() {
    }

    public Sesion(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    

    public LocalDate getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDate fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDate getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDate fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

}
