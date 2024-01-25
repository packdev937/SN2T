package com.example.sn2t.notion.domain.notion;

public enum NotionProperties {
    baseUrl("https://api.notion.com/v1/databases/");

    NotionProperties(String description) {
        this.description = description;
    }

    private String description;

    public String get() {
        return description;
    }
}
