package com.PPAI.backend.backend.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// CONFIGURACION PARA QUE PUEDA RECIBIR Y ENVIAR DATOS DEL FRONT (CORS)
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Define el patrón de URL para los endpoints que deberían permitir CORS
                .allowedOrigins("http://localhost:5173", "http://localhost:3000")  // Permite solicitudes desde el frontend OJO CON EL PUERTO, CAMBIA!!!!
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
                .allowedHeaders("*");  // Permite todos los encabezados
    }
}