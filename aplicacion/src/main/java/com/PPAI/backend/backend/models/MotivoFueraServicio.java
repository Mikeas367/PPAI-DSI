package com.PPAI.backend.backend.models;

import jakarta.persistence.*;

@Entity
public class MotivoFueraServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "motivoTipo_id")
    private MotivoTipo motivoTipo;

    @ManyToOne
    @JoinColumn(name = "cambio_estado_id")
    private CambioEstado cambioEstado;

    public MotivoFueraServicio(String comentario, MotivoTipo motivoTipo) {
        this.comentario = comentario;
        this.motivoTipo = motivoTipo;
    }

    public MotivoFueraServicio() {
    }
    

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public MotivoTipo getMotivoTipo() {
        return motivoTipo;
    }

    public void setMotivoTipo(MotivoTipo motivoTipo) {
        this.motivoTipo = motivoTipo;
    }

}
