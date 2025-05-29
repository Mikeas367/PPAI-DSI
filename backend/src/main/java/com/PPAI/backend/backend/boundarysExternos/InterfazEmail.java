package com.PPAI.backend.backend.boundarysExternos;

import java.time.LocalDate;
import java.util.List;

public class InterfazEmail {
    //private List<String> lisitaDestinatarios;
    private String asunto;
    private String mensaje;
    private String remitente;
    private LocalDate fechaEnvio;

    public InterfazEmail() {}

    public InterfazEmail(List<String> lisitaDestinatarios, String asunto, String mensaje, String remitente, LocalDate fechaEnvio) {
        this.asunto = asunto;
        this.mensaje = mensaje;
        this.remitente = remitente;
        this.fechaEnvio = fechaEnvio;
    }

    public void enviarMail(List<String> lisitaDestinatarios) {
        for(String dest: lisitaDestinatarios){
            System.out.println("Se envia un mail a: " + dest);
        }
    }



    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
}
