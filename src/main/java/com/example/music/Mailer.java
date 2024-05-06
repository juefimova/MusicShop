package com.example.music;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
    public void forgotPassword() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtps.ssl.checkserveridentity", true);
        properties.put("mail.smtp.socketFactory.port", 465);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ss1.SSLSocketFactory");
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.ssl.enable", true);
        properties.put("mail.smtp.port", 465);
        properties.put("mail.debug", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("efimovachuvava@gmail.com", "dsjhnqeyjwivvsji");
            }
        });

        forgot = Context.getInstance().forgot;
        try {
            Message mess = new MimeMessage(session);
            mess.setFrom(new InternetAddress("efimovachuvava@gmail.com"));
            mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(forgot.getForgotEmail().getText()));
            mess.setSubject("Очень важное письмо!!!!!");
            mess.setText("Это письмо было выслано вам для проверки качества отправки писем." +
                    "Спасибо, что поучаствовали в тестировании нашей программы!!");
            Transport.send(mess);
            System.out.println("Ваше сообщение отправлено");

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Не удалось отправить ваше сообщение");
        }
    }
    ForgotPasswordController forgot;
}

