package com.PPAI.backend.backend;

import gui.VentanaPrincipalCerrarOrden;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        //SpringApplication.run(BackendApplication.class, args);


        VentanaPrincipalCerrarOrden ventanaPrincipal = new VentanaPrincipalCerrarOrden();
        ventanaPrincipal.setVisible(true);
    }
}
