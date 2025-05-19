package com.PPAI.backend.backend.models;

public class Estado {
    private String ambito;
    private String nombreEstado;

    // metodos de la solucion

    public boolean sosCompletamenteRealizada(){
        // hay que tener en cuenta q los estados se crean con unos strings especificos
        // osea que si o si va a existir un estado con nombre "Completamente Realizada"
        if (this.nombreEstado == "Completamente Realizada"){
            return true;
        }
        return false;
    }

    public boolean esCerrada(){
        if (this.nombreEstado == "Cerrada"){
            return true;
        }
        return false;
    }

    public boolean esDeAmbitoOrdenDeInspeccion(){
        if (this.ambito == "Ambito Orden"){
            return true;
        }
        return false;
    }




    // contrusctores
    public Estado() {
    }

    public Estado(String ambito, String nombreEstado) {
        this.ambito = ambito;
        this.nombreEstado = nombreEstado;
    }

    // getters and setters

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}
