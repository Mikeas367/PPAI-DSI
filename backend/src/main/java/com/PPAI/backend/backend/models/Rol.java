/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PPAI.backend.backend.models;

/**
 *
 * @author Miqueas
 */
public class Rol {
   private String descripcionRol;
   private String nombre;
   
   public boolean sosResponsableReparacion(){
       if(this.nombre == "Responsable De Reparacion"){
           return true;
       }
       return false;
   }
    public Rol() {
    }

    public Rol(String descripcionRol, String nombre) {
        this.descripcionRol = descripcionRol;
        this.nombre = nombre;
    }
   
    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
   
}
