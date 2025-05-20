package com.PPAI.backend.backend.models;

public class MotivoTipo {
    private int id;
    private String descripcion;


    public MotivoTipo() {
    }

    public MotivoTipo(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
