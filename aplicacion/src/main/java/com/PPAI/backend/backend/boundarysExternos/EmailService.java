package com.PPAI.backend.backend.boundarysExternos;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailService {

    private final String username = "pmiqueas0@gmail.com";
    private final String password = "mpyf nhec eouv yqfr";

    private Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        return Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public void enviarMail(String destinatario, String asunto, String cuerpo) {
        try {
            Message message = new MimeMessage(createSession());
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport.send(message);
            System.out.println("Correo enviado a: " + destinatario);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}