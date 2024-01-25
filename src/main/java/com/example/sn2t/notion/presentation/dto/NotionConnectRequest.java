package com.example.sn2t.notion.presentation.dto;

import org.springframework.util.Assert;

public record NotionConnectRequest(String databaseId, String secretKey) {

    public NotionConnectRequest {
        Assert.notNull(databaseId, "databaseId must not be null");
        Assert.notNull(secretKey, "secretKey must not be null");
    }
}
