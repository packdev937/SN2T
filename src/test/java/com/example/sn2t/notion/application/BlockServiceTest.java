package com.example.sn2t.notion.application;

import static org.junit.jupiter.api.Assertions.*;

import com.example.sn2t.notion.domain.Page;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlockServiceTest {

    @Autowired
    private BlockService blockService;

    @Test
    void 페이지_내용을_마크다운_형식으로_조회한다() {
        // given
        String pageId = "a5f82f91-b68d-4c4e-a3e0-5e64ac410690";

        // when
        Page markdown = blockService.retrieveBlockChildren(pageId);

        // then
        Assertions.assertThat(markdown).isNotNull();
        Assertions.assertThat(markdown.toMarkdown()).isNotBlank();
    }

}