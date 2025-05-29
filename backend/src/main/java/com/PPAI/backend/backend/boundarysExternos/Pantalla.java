package com.PPAI.backend.backend.boundarysExternos;

public class Pantalla {
    private int idPantalla;
    private String imgMostrar;

    public Pantalla(int idPantalla) {
        this.idPantalla = idPantalla;
    }

    public void actualizarPantalla(){
        System.out.println("Actualizando Pantalla");
    }
    public Pantalla() {
    }

    public int getIdPantalla() {
        return idPantalla;
    }

    public void setIdPantalla(int idPantalla) {
        this.idPantalla = idPantalla;
    }

    public String getImgMostrar() {
        return imgMostrar;
    }

    public void setImgMostrar(String imgMostrar) {
        this.imgMostrar = imgMostrar;
    }
}
