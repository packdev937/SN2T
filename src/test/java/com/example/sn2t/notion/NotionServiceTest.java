package com.example.sn2t.notion;

import com.example.sn2t.notion.application.NotionService;
import com.example.sn2t.notion.presentation.dto.NotionConnectRequest;
import com.example.sn2t.notion.presentation.dto.RetrievePageIdsRequest;
import java.util.List;
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

        NotionConnectRequest request = new NotionConnectRequest(wrongDatabaseId, secretKey);

        // when
        notionService.isConnected(request);

        // then
        Assertions.assertThat(notionService.isConnected(request)).isFalse();
    }

    @Test
    void Database에서_페이지_목록을_조회한다() {
        // given
        String databaseId = "c672d22f2c4d48bc8048f869cf0c56f9";
        RetrievePageIdsRequest request = new RetrievePageIdsRequest(databaseId);

        // when
        List<String> pageIds = notionService.retrievePageIds(request);

        // then
        Assertions.assertThat(pageIds).hasSize(2);
    }
}
