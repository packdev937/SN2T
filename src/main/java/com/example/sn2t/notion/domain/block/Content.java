package com.example.sn2t.notion.domain.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @JsonProperty("rich_text")
    private List<RichText> texts;

    public String toMarkdown() {
        StringBuilder markdown = new StringBuilder();

        for (RichText richText : this.texts) {
            markdown.append(richText.toMarkdown());
        }

        if (markdown.length() > 0 && markdown.toString().startsWith("```")) {
            markdown.append("\n```");
        }

        return markdown.toString();
    }
}
