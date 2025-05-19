package com.PPAI.backend.backend.models;

import java.util.Date;

public class EstacionSismologica {
    private int codigoEstacion;
    private String documentoCertificacionAdq;
    private Date fechaSolicitudCertificacion;
    private float latitud;
    private float longitud;
    private String nombre;
    private int nroCertificacionAdquisicion;

    // metodos de solucion

    // constructores

    public EstacionSismologica() {
    }

    public EstacionSismologica(int codigoEstacion, String documentoCertificacionAdq, Date fechaSolicitudCertificacion,
                               float latitud, float longitud, String nombre, int nroCertificacionAdquisicion) {
        this.codigoEstacion = codigoEstacion;
        this.documentoCertificacionAdq = documentoCertificacionAdq;
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }

    // setters and getters
    public int getCodigoEstacion() {
        return codigoEstacion;
    }

    public void setCodigoEstacion(int codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }

    public String getDocumentoCertificacionAdq() {
        return documentoCertificacionAdq;
    }

    public void setDocumentoCertificacionAdq(String documentoCertificacionAdq) {
        this.documentoCertificacionAdq = documentoCertificacionAdq;
    }

    public Date getFechaSolicitudCertificacion() {
        return fechaSolicitudCertificacion;
    }

    public void setFechaSolicitudCertificacion(Date fechaSolicitudCertificacion) {
        this.fechaSolicitudCertificacion = fechaSolicitudCertificacion;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNroCertificacionAdquisicion() {
        return nroCertificacionAdquisicion;
    }

    public void setNroCertificacionAdquisicion(int nroCertificacionAdquisicion) {
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }
}
