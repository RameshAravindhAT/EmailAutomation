package utils;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class SendwithAttachments {

    public static void sendEmail() {
        String to = "ramesh@navadhiti.com";
        String from = "ramesharavindhkarthikeyan.qa@gmail.com";
        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
            	 String password = System.getenv("GMAIL_APP_PASSWORD");
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Automation Test Results");

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find the automation test results attached.");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Attach the Extent Report
            messageBodyPart = new MimeBodyPart();
            String filename = "C:\\Users\\Hp\\OneDrive\\Desktop\\Automation\\EmailAutomation\\ExtentReports\\extentReports.html";
            messageBodyPart.attachFile(new File(filename));
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Sent message successfully...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

