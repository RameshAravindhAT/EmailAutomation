package utils;

import java.util.Properties;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.SubjectTerm;

public class EmailTrigger {

    public static void startEmailPolling() {
        try {
            while (true) {
                String senderEmail = checkForEmail();
                if (senderEmail != null) {
                    System.out.println("Email received from: " + senderEmail);
                    // Trigger your automation workflow here
                    GitHubTrigger.triggerWorkflow(senderEmail);
                }

                // Sleep for 1 second before checking again
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during email polling.", e);
        }
    }

    public static String checkForEmail() throws Exception {
        Properties properties = new Properties();
        properties.put("mail.imap.host", "imap.gmail.com");
        properties.put("mail.imap.port", "993");
        properties.put("mail.imap.ssl.enable", "true");

        Session session = Session.getInstance(properties);
        Store store = session.getStore("imap");
        store.connect(System.getenv("EMAIL_USERNAME"), System.getenv("EMAIL_PASSWORD"));

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_WRITE);

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
