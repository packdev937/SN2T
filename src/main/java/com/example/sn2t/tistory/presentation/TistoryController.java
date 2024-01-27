package com.example.sn2t.tistory.presentation;

import com.example.sn2t.tistory.application.TistoryService;
import com.example.sn2t.tistory.domain.Post;
import com.example.sn2t.tistory.presentation.dto.TistoryUploadRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TistoryController {

    private final TistoryService tistoryService;

    @GetMapping("/upload")
    public void upload(@RequestBody TistoryUploadRequest request) {
        tistoryService.post(request);
    }
}
