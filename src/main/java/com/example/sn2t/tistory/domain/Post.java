package com.example.sn2t.tistory.domain;

import com.example.sn2t.notion.domain.Page;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    private Page page;
    private int visibility;
    private int category;
    private String slogan;
    private String tag;
    private LocalDate publishedDate;


    public Post(Page page, int visibility, int category, String slogan, String tag,
        LocalDate publishedDate) {
        this.page = page;
        this.visibility = visibility;
        this.category = category;
        this.slogan = slogan;
        this.tag = tag;
        this.publishedDate = publishedDate;
    }

    public static Post from(Page page) {
        return new Post(page, 0, 0, "", "", LocalDate.now());
    }
}