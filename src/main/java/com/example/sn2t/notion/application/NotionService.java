package com.example.sn2t.notion.application;

import com.example.sn2t.notion.presentation.dto.NotionConnectRequest;
import com.example.sn2t.notion.presentation.dto.RetrievePageIdsRequest;
import com.example.sn2t.notion.presentation.dto.RetrievePageIdsResponse;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class NotionService {

    private final String notionBaseUrl = "https://api.notion.com/v1/databases/";
    private final WebClient webClient;

    public NotionService() {
        this.webClient = WebClient.builder()
            .baseUrl(notionBaseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    public boolean isConnected(NotionConnectRequest request) {
        try {
            webClient.post()
	.uri(request.databaseId() + "/query")
	.header("Authorization", "Bearer " + request.secretKey())
	.header("Notion-Version", "2022-06-28")
	.retrieve()
	.bodyToMono(String.class)
	.block();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<String> retrievePageIds(RetrievePageIdsRequest request) {
        WebClient webClient = WebClient.builder()
            .baseUrl(notionBaseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

        RetrievePageIdsResponse response = webClient.post()
            .uri(request.databaseId() + "/query")
            .header("Authorization",
	"Bearer " + "secret_DttkpH5a7QYGkZ1Fc7NkooLzTAj5LeXUfjbc8f1LFxY")
            .header("Notion-Version", "2022-06-28")
            .retrieve()
            .bodyToMono(RetrievePageIdsResponse.class)
            .block();

        if (response != null && response.getResults() != null) {
            return response.getResults().stream()
	.map(RetrievePageIdsResponse.Result::getId)
	.collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
