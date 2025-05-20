package com.PPAI.backend.backend.DTOs;

public class MotivoFueraServicioDTO {
    private String descripcion;
    private int idMotivoTipo;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdMotivoTipo() {
        return idMotivoTipo;
    }

    public void setIdMotivoTipo(int idMotivoTipo) {
        this.idMotivoTipo = idMotivoTipo;
    }
}
