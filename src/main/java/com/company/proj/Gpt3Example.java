package com.company.proj;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Gpt3Example {

    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        // Replace with your OpenAI API key
        String apiKey = " ";

        // The prompt for the GPT-3 model
        String prompt = "Translate the following English text to French: 'Hello, how are you?'";

        JSONObject requestBody = new JSONObject();
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", 50); // Adjust the desired length of the generated text

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/engines/davinci/completions"))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject json = new JSONObject(response.body());
                String generatedText = json.getJSONArray("choices").getJSONObject(0).getString("text");
                System.out.println("Generated Text: " + generatedText);
            } else {
                System.err.println("Request failed with code: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
