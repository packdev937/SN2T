package com.example.sn2t.notion;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

class NotionService {

    private String notionBaseUrl = "https://api.notion.com/v1/databases/";

    public boolean isConnected(NotionConnectRequest request) {
        WebClient webClient = WebClient.builder()
            .baseUrl(notionBaseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

        String response = null;
        try {
            response = webClient.post()
	.uri(request.databaseId() + "/query")
	.header("Authorization", "Bearer " + request.secretKey())
	.header("Notion-Version", "2022-06-28")
	.retrieve()
	.bodyToMono(String.class)
	.block();
        } catch (Exception e) {
            return false;
        }
        System.out.println(response);
        return true;
    }
}
