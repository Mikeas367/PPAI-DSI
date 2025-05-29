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

    public void crearNuevoCambioEstado(LocalDate fechaHoraInicio, List<MotivoFueraServicio> motivoFueraServicio, Estado estado, Empleado responsable) {
        System.out.println("------------>creando cambio estado en el Sismografo");
        CambioEstado cambioEstado = new CambioEstado(fechaHoraInicio, estado, responsable);
        cambioEstado.setMotivosFueraServicio(motivoFueraServicio);
        cambioEstado.setFechaHoraFin(null);
        cambioEstados.add(cambioEstado);
    }

    public void ponerEnFueraServicio(Estado estado, List<MotivoFueraServicio> motivoFueraServicios, LocalDate fechaHoraActual, Empleado responsable) {
        for (MotivoFueraServicio m : motivoFueraServicios){
            System.out.println("Esto son los Motivos que me llegan al sismografo para crear el CE: " + m.getComentario());
        }
        System.out.println("----> Poniendo en fuera de servicio el sismografo");
        CambioEstado ce = buscarEstadoActual();
        ce.setFechaHoraFin(fechaHoraActual);
        setEstadoActual(estado);
        System.out.println("Se cambio el estadoActual del simografo a: " + estadoActual.getNombreEstado());
        
        System.out.println("el ce del simografo que se encuentra es: " + ce.getEstado().getNombreEstado());
        crearNuevoCambioEstado(fechaHoraActual, motivoFueraServicios, estado, responsable);
        System.out.println("El nuevo Cambio de Estado del sismografo es: " + buscarEstadoActual().getEstado().getNombreEstado());

    }

    public CambioEstado buscarEstadoActual(){
        for (CambioEstado cambioEstado : cambioEstados) {
            if (cambioEstado.esActual()){
                return cambioEstado;
            }
        }
        return null;
    }

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
