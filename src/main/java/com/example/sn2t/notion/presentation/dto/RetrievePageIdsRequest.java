package com.example.sn2t.notion.presentation.dto;

import org.springframework.util.Assert;

public record RetrievePageIdsRequest(String databaseId) {

    public RetrievePageIdsRequest {
        Assert.notNull(databaseId, "databaseId must not be null");
    }
}
