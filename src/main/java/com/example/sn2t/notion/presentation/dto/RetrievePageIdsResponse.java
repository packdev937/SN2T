package com.example.sn2t.notion.presentation.dto;

import java.util.List;
import lombok.Data;
import lombok.Getter;

@Data
public class RetrievePageIdsResponse {

    private List<Result> results;

    @Getter
    public static class Result {

        private String id;
    }

}
