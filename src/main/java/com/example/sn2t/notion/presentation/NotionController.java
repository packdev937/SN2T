package com.example.sn2t.notion.presentation;

import com.example.sn2t.notion.application.DatabaseService;
import com.example.sn2t.notion.presentation.dto.RetrievePageIdsRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotionController {

    private final DatabaseService databaseService;

    @GetMapping("/notion")
    public List<String> getPageIds(@RequestBody RetrievePageIdsRequest request) {
        return databaseService.retrievePageIds(request);
    }
}
