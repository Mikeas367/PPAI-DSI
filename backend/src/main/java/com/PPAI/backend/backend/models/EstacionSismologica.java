package com.PPAI.backend.backend.models;

import java.util.Date;
import java.util.List;

public class EstacionSismologica {
    private int codigoEstacion;
    private String documentoCertificacionAdq;
    private Date fechaSolicitudCertificacion;
    private float latitud;
    private float longitud;
    private String nombre;
    private int nroCertificacionAdquisicion;

    List<Sismografo> sismografos; //estos sismografos
    // SON TEMPORALES SOLO LO USO PARA RECORRER PARA ENCONTRAR EL SIS QUE LE PERTENECE A ESTA ES

    public void setSismografos(List<Sismografo> sismografos) {
        this.sismografos = sismografos;
    }

    // metodos de solucion


    public Sismografo buscarSismografo(){
        for(Sismografo sismografo : sismografos){
            if (sismografo.sosDeEstacionSismologica(this)){
                return sismografo;
            }
        }
        return null;
    }

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
