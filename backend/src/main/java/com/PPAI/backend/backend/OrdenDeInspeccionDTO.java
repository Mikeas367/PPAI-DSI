package com.PPAI.backend.backend;

import java.time.LocalDate;
import java.util.Date;

// los datos que necesitaria seria -> numeroDeOrden
// -> fechaHoraFinalizacion
// -> nombre de la Estacion Sismologica
public class OrdenDeInspeccionDTO {

    private int numeroOrden;
    private LocalDate  fechaHoraFinalizacion;
    private String nombreEstacionSismologica;

    public OrdenDeInspeccionDTO(int numeroOrden, LocalDate  fechaHoraFinalizacion, String nombreEstacionSismologica) {
        this.numeroOrden = numeroOrden;
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
        this.nombreEstacionSismologica = nombreEstacionSismologica;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public LocalDate getFechaHoraFinalizacion() {
        return fechaHoraFinalizacion;
    }

    public void setFechaHoraFinalizacion(LocalDate  fechaHoraFinalizacion) {
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
    }

    public String getNombreEstacionSismologica() {
        return nombreEstacionSismologica;
    }

    public void setNombreEstacionSismologica(String nombreEstacionSismologica) {
        this.nombreEstacionSismologica = nombreEstacionSismologica;
    }
}
