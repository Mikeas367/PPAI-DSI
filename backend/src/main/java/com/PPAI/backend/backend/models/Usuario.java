package com.PPAI.backend.backend.models;

public class Usuario {
    private String nombreUsuario;
    private String contrasenia;

    private Empleado empleado;

    // constructores

    public Usuario() {
    }
    
    public Empleado getRILogueado(){
        return this.empleado;
    }

    public Usuario(String nombreUsuario, String contrasenia, Empleado empleado) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.empleado = empleado;
    }

    // getters and setters

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
