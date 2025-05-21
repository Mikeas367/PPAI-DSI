package com.PPAI.backend.backend.DTOs;

public class MotivoFueraServicioDTO {
    private String comentario;
    private int idMotivoTipo;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdMotivoTipo() {
        return idMotivoTipo;
    }

    public void setIdMotivoTipo(int idMotivoTipo) {
        this.idMotivoTipo = idMotivoTipo;
    }
}
