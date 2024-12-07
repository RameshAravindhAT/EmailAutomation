package utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GitHubTrigger {

    public static void triggerWorkflow(String email) throws Exception {
        String url = "https://api.github.com/repos/RameshAravindhAT/AllTeacherSeleniumArchitectGitHubActions/actions/workflows/CreateReports.yml/dispatches";
        String token = System.getenv("GITHUB_TOKEN");

        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("GitHub token not set in environment variables.");
        }

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        // Set headers
        httpPost.setHeader("Authorization", "Bearer " + token);
        httpPost.setHeader("Accept", "application/vnd.github.v3+json");

        // Set JSON payload
        String jsonPayload = "{ \"ref\": \"main\", \"inputs\": { \"email\": \"" + email + "\" } }";
        httpPost.setEntity(new StringEntity(jsonPayload));

        // Execute the request
        try (CloseableHttpResponse response = client.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 204) { // Success
                System.out.println("Workflow triggered successfully!");
            } else {
                System.err.println("Failed to trigger workflow. HTTP Status Code: " + statusCode);
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response.getEntity().getContent()));
                StringBuilder responseString = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseString.append(line);
                }
                System.err.println("Response: " + responseString);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error triggering GitHub workflow.", e);
        } finally {
            client.close();
        }
    }
}
