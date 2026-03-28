package com.PPAI.backend.backend.boundarysExternos;

import com.PPAI.backend.backend.interfaces.IObservador;
import com.PPAI.backend.backend.models.MotivoFueraServicio;
import com.PPAI.backend.backend.models.MotivoTipo;

import java.time.LocalDate;
import java.util.List;

public class InterfazEmail implements IObservador {
    private List<String> destinatarios;
    private EmailService emailService;

    public InterfazEmail(List<String> destinatarios) {
        this.destinatarios = destinatarios;
        this.emailService = new EmailService();
    }

    @Override
    public void actualizar(int identificadorSismografo, String estado, LocalDate fecha, String mensaje) {
        for (String destinatario : this.destinatarios){
            String asunto = "Sismógrafo " + identificadorSismografo + " fuera de servicio";
            emailService.enviarMail(destinatario, asunto, mensaje);
        }
    }
}
