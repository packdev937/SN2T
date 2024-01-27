package com.example.sn2t.notion.application;

import static com.example.sn2t.notion.domain.notion.NotionProperties.*;

import com.example.sn2t.notion.domain.Page;
import com.example.sn2t.notion.domain.notion.NotionProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BlockService {

    private final WebClient webClient;

    public BlockService() {
        this.webClient = WebClient.builder()
            .baseUrl(blockBaseUrl.get())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
    }

    public Page retrieveBlockChildren(String pageId) {
        Page page = webClient.get()
            .uri(pageId + "/children?page_size=100")
            .header("Authorization", "Bearer " + NotionProperties.secretKey.get())
            .header("Notion-Version", "2022-06-28")
            .retrieve()
            .bodyToMono(Page.class)
            .block();

        return page;
    }
}
