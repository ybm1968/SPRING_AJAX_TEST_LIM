package com.joeun.board.dto;

import lombok.Data;

@Data
public class Page {
    private int page;           // 현재 페이지 번호
    private int rows;           // 페이지당 게시글 수
    private int pageCount;      // 노출 페이지 개수
    private int totalCount;     // 전체 게시글 수
}
