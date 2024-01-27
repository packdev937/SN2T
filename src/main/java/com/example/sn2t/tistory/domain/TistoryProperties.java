package com.example.sn2t.tistory.domain;

public enum TistoryProperties {
    baseUrl("https://www.tistory.com/apis"),
    accessToken("5a47645f5bd79ebd7cead88015943066_08cb05e767bcc8293e9b31ac52424cfa"),
    blogName("packdev937"),
    blogPassword("0314");

    String value;

    TistoryProperties(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
