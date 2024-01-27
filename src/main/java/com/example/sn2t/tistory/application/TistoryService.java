package com.example.sn2t.tistory.application;

import com.example.sn2t.notion.application.BlockService;
import com.example.sn2t.notion.application.DatabaseService;
import com.example.sn2t.notion.domain.Page;
import com.example.sn2t.tistory.domain.Post;
import com.example.sn2t.tistory.domain.TistoryProperties;
import com.example.sn2t.tistory.presentation.dto.TistoryUploadRequest;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.BodyInserters;

@Service
public class TistoryService {

    private final WebClient webClient;
    private final BlockService blockService;

    public TistoryService() {
        this.webClient = WebClient.builder()
            .baseUrl(TistoryProperties.baseUrl.get())
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .build();
        this.blockService = new BlockService();
    }

    public void post(TistoryUploadRequest request) {
        Page page = blockService.retrieveBlockChildren(request.pageId());
        Post post = Post.from(page);
        webClient.post()
            .uri("/post/write")
            .header("Authorization", "Bearer " + TistoryProperties.accessToken.get())
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData("output", "html")
	.with("blogName", TistoryProperties.blogName.get())
	.with("title", post.getPage().getTitle())
	.with("content", post.getPage().toMarkdown())
	.with("visibility", String.valueOf(post.getVisibility()))
	.with("category", String.valueOf(post.getCategory()))
	.with("published", LocalDate.now().toString())
	.with("slogan", post.getSlogan())
	.with("tag", post.getTag())
	.with("acceptComment", "1")
	.with("password", TistoryProperties.blogPassword.get()))
            .retrieve()
            .bodyToMono(String.class);
    }
}
