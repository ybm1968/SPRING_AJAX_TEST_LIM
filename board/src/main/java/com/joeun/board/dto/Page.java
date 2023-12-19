package com.joeun.board.dto;

import lombok.Data;

@Data
public class Page {
    private int currentPost;
    private int page;
    private int postPerPage;
}
