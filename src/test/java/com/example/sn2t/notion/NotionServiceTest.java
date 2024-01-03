package com.example.sn2t.notion;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}
