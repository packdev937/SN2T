package com.example.sn2t.notion.application;


import static com.example.sn2t.notion.domain.notion.NotionProperties.*;

import com.example.sn2t.notion.domain.notion.NotionProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PageService {

    private final WebClient webClient;

    public PageService() {
        this.webClient = WebClient.builder()
            .baseUrl(pageBaseUrl.get())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    public void updateStatusProperties(String pageId, String secretKey, String to) {
        String jsonBody = "{\n"
            + "  \"properties\": {\n"
            + "    \"status\": {\n"
            + "      \"select\": {\n"
            + "        \"name\": \"" + to + "\"\n"
            + "      }\n"
            + "    }\n"
            + "  }\n"
            + "}\n";

        webClient.patch()
            .uri(pageId)
            .header("Authorization", "Bearer " + secretKey)
            .header("Notion-Version", "2022-06-28")
            .bodyValue(jsonBody)
            .retrieve()
            .toBodilessEntity()
            .block();
    }
}
