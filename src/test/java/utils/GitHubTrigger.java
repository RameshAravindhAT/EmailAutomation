package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import io.github.cdimascio.dotenv.Dotenv;

public class GitHubTrigger {

    public static void triggerWorkflow(String branch) throws Exception {
    	String url = "https://api.github.com/repos/RameshAravindhAT/EmailAutomation/actions/workflows/maven.yml/dispatches";


        // Load environment variables from the .env file
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("GITHUB_TOKEN");

        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("GitHub token not set in environment variables.");
        }

        // Initialize the HTTP client to make the request
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        // Set headers to authenticate and specify the GitHub API version
        httpPost.setHeader("Authorization", "Bearer " + token);
        httpPost.setHeader("Accept", "application/vnd.github.v3+json");

        // Construct the payload to trigger the GitHub workflow (passing 'branch' as input)
        String jsonPayload = "{ \"ref\": \"" + branch + "\", \"inputs\": { \"branch\": \"" + branch + "\" } }";
        httpPost.setEntity(new StringEntity(jsonPayload));

        // Execute the API request and process the response
        try (CloseableHttpResponse response = client.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            
            if (statusCode == 204) {  // If the status code is 204, the workflow was triggered successfully
                System.out.println("Workflow triggered successfully for branch: " + branch);
                
                // Optionally, check the workflow status (after triggering) or take further actions
                checkWorkflowStatus();
            } else {
                // Log the response and handle errors
                System.err.println("Failed to trigger workflow. HTTP Status Code: " + statusCode);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder responseString = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseString.append(line);
                }
                System.err.println("Response: " + responseString);

                // Handle specific error cases based on the response
                if (responseString.toString().contains("No ref found for")) {
                    System.err.println("Error: The specified branch does not exist in the repository.");
                } else {
                    System.err.println("Error: Unable to trigger the workflow. Please check the GitHub API response for more details.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error triggering GitHub workflow.", e);
        } finally {
            client.close();
        }
    }

    // Optional: Method to check the status of the workflow after triggering it
    private static void checkWorkflowStatus() throws Exception {
        // You can query the GitHub API to get the status of the latest workflow run triggered for the branch
        String url = "https://api.github.com/repos/RameshAravindhAT/EmailAutomation/actions/runs";
        
        // Fetch the latest workflow runs
        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("GITHUB_TOKEN");
        
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        
        // Set authorization header
        httpGet.setHeader("Authorization", "Bearer " + token);
        httpGet.setHeader("Accept", "application/vnd.github.v3+json");
        
        try (CloseableHttpResponse response = client.execute(httpGet)) {
            int statusCode = response.getStatusLine().getStatusCode();
            
            if (statusCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuilder responseString = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    responseString.append(line);
                }
                // Parse and display workflow status
                System.out.println("Workflow status: " + responseString);
            } else {
                System.err.println("Failed to fetch workflow status. HTTP Status Code: " + statusCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error checking GitHub workflow status.", e);
        } finally {
            client.close();
        }
    }

    public static void main(String[] args) {
        try {
            // Replace with the branch name received from email
            String branch = "Developement";
            triggerWorkflow(branch);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
