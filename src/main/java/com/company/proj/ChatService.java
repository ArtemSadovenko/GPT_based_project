package com.company.proj;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;

@Service
public class ChatService {
    @Autowired
    WebClient webClient;

    public void start(){
        String prompt = "Let`s play a game. Here rules how to play:\n" +
                "- We one by one have to name existing country\n" +
                "- Country first letter have to be the same as the last letter of previous country, named by opponent\n" +
                "- If you can`t find any more suitable country say\"I give up\"\n" +
                "- you response should be only name of country\n" +
                "- if I name wrong country say: \"You lose\" \n" +
                "- if you understand all the rules just response \"Ok, i am ready\"";


        String apiKey = getApiKey();

        JSONObject requestBody = new JSONObject();
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", 50);

        String response = webClient.post()
                .uri(URI.create("https://api.openai.com/v1/engines/davinci/completions"))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .body(requestBody, JSONObject.class)
                .retrieve()
                .bodyToMono();
    }

    private String getApiKey(){
        try (BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "-1";
        }
    }

}
