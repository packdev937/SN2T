package com.example.sn2t.notion.domain.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RichText {

    private String type;

    @JsonProperty("plain_text")
    private String plainText;
    private Annotations annotations;

    public String toMarkdown() {
        String text = plainText;

        if (annotations.isBold()) {
            text = "**" + text + "**";
        }
        if (annotations.isItalic()) {
            text = "*" + text + "*";
        }
        if (annotations.isStrikethrough()) {
            text = "~~" + text + "~~";
        }
        if (annotations.isUnderline()) {
            text = "<u>" + text + "</u>";
        }
        if (annotations.isCode()) {
            text = "`" + text + "`";
        }

        return text;
    }

    @Data
    static class Annotations {

        private boolean bold;
        private boolean italic;
        private boolean strikethrough;
        private boolean underline;
        private boolean code;
        private String color;
    }
}

