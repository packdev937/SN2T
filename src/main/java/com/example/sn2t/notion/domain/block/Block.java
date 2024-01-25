package com.example.sn2t.notion.domain.block;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Block {

    private String id;
    private String type;
    @JsonAlias({"heading_1", "heading_2", "heading_3", "paragraph", "code"})
    private Content content;

    public String toMarkdown() {
        String markdownPrefix = getMarkdownPrefix(type);
        String markdownSuffix = getMarkdownSuffix(type);
        return markdownPrefix + (content != null ? content.toMarkdown() : "") + markdownSuffix;
    }

    private String getMarkdownPrefix(String type) {
        if (type == null) {
            return "";
        }

        switch (type) {
            case "heading_1":
                return "# ";
            case "heading_2":
                return "## ";
            case "heading_3":
                return "### ";
            case "code":
                return "```java\n";
            default:
                return "";
        }
    }

    private String getMarkdownSuffix(String type) {
        if (type == null) {
            return "";
        }

        switch (type) {
            case "code":
                return "\n```\n";
            default:
                return "\n";
        }
    }
}