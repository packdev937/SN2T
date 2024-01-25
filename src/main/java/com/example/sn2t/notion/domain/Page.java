package com.example.sn2t.notion.domain;

import com.example.sn2t.notion.domain.block.Block;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Page {

    private List<Block> results;

    public String toMarkdown() {
        StringBuilder markdown = new StringBuilder();

        for (Block block : this.results) {
            markdown.append(block.toMarkdown());
        }

        return markdown.toString();
    }
}
