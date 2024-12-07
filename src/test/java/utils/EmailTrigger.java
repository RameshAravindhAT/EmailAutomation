package utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.search.SubjectTerm;
import io.github.cdimascio.dotenv.Dotenv; // Import Dotenv

public class EmailTrigger {

    public static void startEmailPolling() {
        try {
            while (true) {
                String senderEmail = checkForEmail();
                if (senderEmail != null) {
                    System.out.println("Email received from: " + senderEmail);
                    // Trigger your GitHub workflow here
                    GitHubTrigger.triggerWorkflow(senderEmail);
                }

                // Sleep for 1 minute before checking again (more efficient than 1 second)
                Thread.sleep(60000); // 1 minute
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during email polling.", e);
        }
    }

    public static String checkForEmail() throws Exception {
        // Load environment variables from the .env file
        Dotenv dotenv = Dotenv.load();

        // Set up email properties for Gmail
        Properties properties = new Properties();
        properties.put("mail.imap.host", "imap.gmail.com");
        properties.put("mail.imap.port", "993");
        properties.put("mail.imap.ssl.enable", "true");

        // Create a session to connect to Gmail using the email credentials from .env file
        Session session = Session.getInstance(properties);
        Store store = session.getStore("imap");

        // Connect using environment variables for email credentials
        store.connect(dotenv.get("EMAIL_USERNAME"), dotenv.get("EMAIL_PASSWORD"));

        // Access inbox folder and search for specific subject
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);

        // Search for email with subject "Run Automation test"
        Message[] messages = inbox.search(new SubjectTerm("Run Automation test"));
        if (messages.length > 0) {
            Message message = messages[0];
            String senderEmail = ((InternetAddress) message.getFrom()[0]).getAddress();
            message.setFlag(Flags.Flag.SEEN, true); // Mark as read
            return senderEmail;
        }

        inbox.close(false);
        store.close();
        return null;
    }
}
