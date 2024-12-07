package utils;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import io.github.cdimascio.dotenv.Dotenv; // Import Dotenv

public class EmailReply {

    // Method to send the report to a single recipient
    public static void sendReport(String recipient, String reportPath) throws Exception {
        // Load environment variables from the .env file
        Dotenv dotenv = Dotenv.load();

        // Set up properties for the email session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create the session using Dotenv to get credentials
        Session session = Session.getInstance(properties,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        // Use Dotenv to fetch email credentials
                        return new PasswordAuthentication(dotenv.get("EMAIL_USERNAME"), dotenv.get("EMAIL_PASSWORD"));
                    }
                });

        // Create the message
        Message message = new MimeMessage(session);
        // Use Dotenv to get the sender's email address
        message.setFrom(new InternetAddress(dotenv.get("EMAIL_USERNAME")));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject("Automation Test Report");

        // Attach the report
        MimeBodyPart attachment = new MimeBodyPart();
        attachment.attachFile(reportPath);

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(attachment);

        message.setContent(multipart);

        // Send the email
        Transport.send(message);
        System.out.println("Report sent to: " + recipient);
    }

    public static void main(String[] args) throws Exception {
        // Load recipients from a file or list (hardcoded for now)
        List<String> recipients = Arrays.asList("ramesharavindhkarthikeyan.qa@gmail.com","reslirocker@gmail.com");

        // Specify the path to the report file
        String reportPath = "path/to/report.pdf"; // Replace with the actual file path

        // Send the report to each recipient
        for (String recipient : recipients) {
            sendReport(recipient, reportPath);
        }
    }
}
