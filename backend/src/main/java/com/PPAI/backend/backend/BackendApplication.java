package com.PPAI.backend.backend;

import gui.PantallaCancelacion;
import com.PPAI.backend.backend.controllers.GestorOrdenInspeccion;
import com.PPAI.backend.backend.models.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		//SpringApplication.run(BackendApplication.class, args);
                
                   PantallaCancelacion pantalla = new PantallaCancelacion();
                   pantalla.setVisible(true);
	}

}
