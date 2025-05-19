package com.PPAI.backend.backend.models;

import java.time.LocalDate;
import java.util.List;

public class Sismografo {
    private LocalDate fechaAdquisision;
    private int identificadorSismografo; // id normal
    private String nroSerie; // seria como los digitos del codigo de barras que puede tener letras

    private EstacionSismologica estacionSismologica;
    private Estado estadoActual;
    private List<CambioEstado> cambioEstados;




    public boolean sosDeEstacionSismologica(EstacionSismologica estacionSismologica) {
        if(this.estacionSismologica.equals(estacionSismologica)){
            return true;
        }
        return false;
    }

    public Sismografo(LocalDate fechaAdquisision, int identificadorSismografo, String nroSerie, EstacionSismologica estacionSismologica, Estado estadoActual, List<CambioEstado> cambioEstados) {
        this.fechaAdquisision = fechaAdquisision;
        this.identificadorSismografo = identificadorSismografo;
        this.nroSerie = nroSerie;
        this.estacionSismologica = estacionSismologica;
        this.estadoActual = estadoActual;
        this.cambioEstados = cambioEstados;
    }

    public LocalDate getFechaAdquisision() {
        return fechaAdquisision;
    }

    public void setFechaAdquisision(LocalDate fechaAdquisision) {
        this.fechaAdquisision = fechaAdquisision;
    }

    public int getIdentificadorSismografo() {
        return identificadorSismografo;
    }

    public void setIdentificadorSismografo(int identificadorSismografo) {
        this.identificadorSismografo = identificadorSismografo;
    }

    public String getNroSerie() {
        return nroSerie;
    }

    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public EstacionSismologica getEstacionSismologica() {
        return estacionSismologica;
    }

    public void setEstacionSismologica(EstacionSismologica estacionSismologica) {
        this.estacionSismologica = estacionSismologica;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public List<CambioEstado> getCambioEstados() {
        return cambioEstados;
    }

    public void setCambioEstados(List<CambioEstado> cambioEstados) {
        this.cambioEstados = cambioEstados;
    }
}
