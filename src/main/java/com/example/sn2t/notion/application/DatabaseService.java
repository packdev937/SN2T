package com.example.sn2t.notion.application;

import static com.example.sn2t.notion.domain.notion.NotionProperties.*;

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
public class DatabaseService {

    private final WebClient webClient;
    private final String secretKey = "secret_DttkpH5a7QYGkZ1Fc7NkooLzTAj5LeXUfjbc8f1LFxY";

    public DatabaseService() {
        this.webClient = WebClient.builder()
            .baseUrl(databaseBaseUrl.get())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    private <T> T query(String databaseId, String secretKey, Class<T> responseType,
        String jsonBody) {
        return webClient.post()
            .uri(databaseId + "/query")
            .header("Authorization", "Bearer " + secretKey)
            .header("Notion-Version", "2022-06-28")
            .bodyValue(jsonBody)
            .retrieve()
            .bodyToMono(responseType)
            .block();
    }

    public boolean isConnected(NotionConnectRequest request) {
        try {
            query(request.databaseId(), request.secretKey(), String.class, "{}");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<String> retrievePageIds(RetrievePageIdsRequest request) {
        String filter = "{"
            + "\"filter\": {"
            + "\"property\": \"status\","
            + "\"select\": {"
            + "\"equals\": \"UPLOADING\""
            + "}"
            + "}"
            + "}";

        RetrievePageIdsResponse response = query(
            request.databaseId(),
            secretKey,
            RetrievePageIdsResponse.class,
            filter
        );

        if (response != null && response.getResults() != null) {
            return response.getResults().stream()
	.map(RetrievePageIdsResponse.Result::getId)
	.collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
