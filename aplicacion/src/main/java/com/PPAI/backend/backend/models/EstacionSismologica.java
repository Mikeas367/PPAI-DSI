package com.PPAI.backend.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.List;
@Entity
public class EstacionSismologica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int codigoEstacion;
    private String documentoCertificacionAdq;
    private Date fechaSolicitudCertificacion;
    private float latitud;
    private float longitud;
    private String nombre;
    private int nroCertificacionAdquisicion;

    //List<Sismografo> sismografos; //estos sismografos
    // SON TEMPORALES SOLO LO USO PARA RECORRER PARA ENCONTRAR EL SIS QUE LE PERTENECE A ESTA ES

    //public void setSismografos(List<Sismografo> sismografos) {
        //this.sismografos = sismografos;
    //}

    // metodos de solucion


    public Sismografo buscarSismografo(List<Sismografo> sismografos) {
        // en vez de tener una lista lo mejor seria tener un BD y llamar al repo directamente aca para
        // obtener todos los simografos, pero como no tenemos db voy a crear una lista
        for(Sismografo s : sismografos){
            if (s.sosDeEstacionSismologica(this)){
                return s;
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
