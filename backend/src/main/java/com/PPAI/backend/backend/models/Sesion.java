package com.PPAI.backend.backend.models;

public class Sesion {

    private Usuario usuario;

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
    
    public Empleado obtenerEmpleado(){
       return usuario.getRILogueado();
    }
}
