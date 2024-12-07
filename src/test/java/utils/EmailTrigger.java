package utils;

import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.SubjectTerm;

import org.testng.annotations.Test;

import io.github.cdimascio.dotenv.Dotenv;

public class EmailTrigger {

    @Test
    public static void startEmailPolling() {
        try {
            while (true) {
                String branch = checkForEmail();
                if (branch != null) {
                    System.out.println("Connecting Email...");
                    System.out.println("Email received for branch: " + branch);
                    // Trigger your GitHub workflow here (You can add your logic to trigger the GitHub workflow)
                    GitHubTrigger.triggerWorkflow(branch);
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

        // Access inbox folder and open it in READ_WRITE mode
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE); // Ensure folder is opened in read-write mode

        // Log folder access mode for debugging
        System.out.println("Folder mode: " + (inbox.getMode() == Folder.READ_WRITE ? "READ_WRITE" : "READ_ONLY"));

        // Search for email with subject "Run Automation test on branch: development"
        Message[] messages = inbox.search(new SubjectTerm("Run Automation test on branch:"));
        if (messages.length > 0) {
            Message message = messages[0];
            String senderEmail = ((InternetAddress) message.getFrom()[0]).getAddress();
            String branchName = extractBranchName(message.getSubject());
            message.setFlag(Flags.Flag.SEEN, true); // Mark as read
            inbox.close(false); // Close the inbox folder
            store.close(); // Close the connection to the store
            return branchName;
        }

        inbox.close(false); // Close the inbox folder
        store.close(); // Close the connection to the store
        return null;
    }

    private static String extractBranchName(String subject) {
        // Implement logic to extract the branch name from the email subject
        String[] parts = subject.split(":");
        if (parts.length >= 2) {
            return parts[1].trim();
        }
        return null;
    }
}