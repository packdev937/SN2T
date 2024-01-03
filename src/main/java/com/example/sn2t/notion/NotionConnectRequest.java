package com.example.sn2t.notion;

import org.springframework.util.Assert;

record NotionConnectRequest(String databaseId, String secretKey) {

    NotionConnectRequest {
        Assert.notNull(databaseId, "databaseId must not be null");
        Assert.notNull(secretKey, "secretKey must not be null");
    }
}
