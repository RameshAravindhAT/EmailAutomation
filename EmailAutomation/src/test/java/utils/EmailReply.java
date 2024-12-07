package utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailReply {

    public static void sendReport(String recipient, String reportPath) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(System.getenv("EMAIL_USERNAME"), System.getenv("EMAIL_PASSWORD"));
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(System.getenv("EMAIL_USERNAME")));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject("Automation Test Report");

        // Attach the report
        MimeBodyPart attachment = new MimeBodyPart();
        attachment.attachFile(reportPath);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(attachment);

        message.setContent(multipart);

        Transport.send(message);
        System.out.println("Report sent successfully!");
    }
}
