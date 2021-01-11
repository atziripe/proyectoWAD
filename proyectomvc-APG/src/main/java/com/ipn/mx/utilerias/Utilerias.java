/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ipn.mx.utilerias;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author Atziri Perez
 */
public class Utilerias {
    public void enviarCorreo(String destinatario, String asunto, String mensaje){
        try {
            Properties propiedades = new Properties();
            propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
            propiedades.setProperty("mail.smtp.starttls.enable", "true");
            propiedades.setProperty("mail.smtp.port", "587");
            propiedades.setProperty("mail.smtp.user", "weas.wad.2020@gmail.com");
            propiedades.setProperty("mail.smtp.auth", "true");
            
            Session session = Session.getDefaultInstance(propiedades);
            MimeMessage elMensaje = new MimeMessage(session);
            
            elMensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            elMensaje.setSubject(asunto);
            elMensaje.setText(mensaje);
            
            Transport t = session.getTransport("smtp");
            t.connect("weas.wad.2020@gmail.com", "weasWAD123");
            
            //myaccount.google.com/lesssecureapp
            
            t.sendMessage(elMensaje, elMensaje.getAllRecipients());
            t.close();
            
        } catch (AddressException ex) {
            Logger.getLogger(Utilerias.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Utilerias.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
//    public static void main(String[] args) {
//        Utilerias mandarCorreo = new Utilerias();
//        
//        String destinatario = "atziripg.99@gmail.com";
//        //String destinatario = "skatepro762@gmail.com";
//        String asunto = "Prueba";
//        String texto = "Hola esta es una prueba configurado avast";
//        
//        mandarCorreo.enviarCorreo(destinatario, asunto, texto);
//                
//    }
}
