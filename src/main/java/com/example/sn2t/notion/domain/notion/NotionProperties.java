package com.example.sn2t.notion.domain.notion;

public enum NotionProperties {
    databaseBaseUrl("https://api.notion.com/v1/databases/"),
    blockBaseUrl("https://api.notion.com/v1/blocks/"),
    pageBaseUrl("https://api.notion.com/v1/pages/"),
    secretKey("secret_DttkpH5a7QYGkZ1Fc7NkooLzTAj5LeXUfjbc8f1LFxY");

    NotionProperties(String description) {
        this.description = description;
    }

    private String description;

    public String get() {
        return description;
    }
}
