package com.PPAI.backend.backend.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class CambioEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate fechaHoraFin;
    private LocalDate fechaHoraInicio;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;
    @OneToMany(mappedBy = "cambioEstado", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MotivoFueraServicio> motivosFueraServicio;

    @ManyToOne
    @JoinColumn(name = "responsbleDeInspeccion_id")
    private Empleado responsableDeInspeccion;

    @ManyToOne
    @JoinColumn(name = "sismografo_id")
    private Sismografo sismografo;

    public CambioEstado() {

    }

    public void mostrarMotivos(){
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

    public void  setSismografo(Sismografo sismografo) {
        this.sismografo = sismografo;
    }
}
