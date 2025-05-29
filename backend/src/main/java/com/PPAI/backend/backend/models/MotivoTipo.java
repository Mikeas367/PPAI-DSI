package com.PPAI.backend.backend.models;

public class MotivoTipo {
    private String descripcion;


    public MotivoTipo() {
    }

    public MotivoTipo(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @Override
    public String toString() {
        return descripcion; // esto es importante para que se vea bien en el JComboBox
    }
}
