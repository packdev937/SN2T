package com.example.sn2t.notion;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;

public class NotionServiceTest {

    private NotionService notionService;

    @BeforeEach
    void setUp() {
        notionService = new NotionService();
    }

    @Test
    void Notion_연결이_되었다면_true를_반환한다() {
        // given
        String databaseId = "c672d22f2c4d48bc8048f869cf0c56f9";
        String secretKey = "secret_DttkpH5a7QYGkZ1Fc7NkooLzTAj5LeXUfjbc8f1LFxY";

        // when
        NotionConnectRequest request = new NotionConnectRequest(databaseId, secretKey);
        notionService.isConnected(request);

        // given
        Assertions.assertThat(notionService.isConnected(request)).isTrue();
    }

    @Test
    void Notion_연결이_잘못되었다면_false를_반환한다() {
        // given
        String wrongDatabaseId = "c672d22f2c4d48bc8048f869cf0c56f8";
        String secretKey = "secret_DttkpH5a7QYGkZ1Fc7NkooLzTAj5LeXUfjbc8f1LFxY";

        // when
        NotionConnectRequest request = new NotionConnectRequest(wrongDatabaseId, secretKey);
        notionService.isConnected(request);

        // then
        Assertions.assertThat(notionService.isConnected(request)).isFalse();
    }

    private record NotionConnectRequest(String databaseId, String secretKey) {

        private NotionConnectRequest {
            Assert.notNull(databaseId, "databaseId must not be null");
            Assert.notNull(secretKey, "secretKey must not be null");
        }
    }

    private class NotionService {

        private String notionBaseUrl = "https://api.notion.com/v1/databases/";

        public boolean isConnected(NotionConnectRequest request) {
            WebClient webClient = WebClient.builder()
	.baseUrl(notionBaseUrl)
	.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	.build();

            String response = null;
            try {
	response = webClient.post()
	    .uri(request.databaseId + "/query")
	    .header("Authorization", "Bearer " + request.secretKey)
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
}
