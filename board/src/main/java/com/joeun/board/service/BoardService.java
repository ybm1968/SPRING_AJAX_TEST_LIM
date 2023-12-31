package com.joeun.board.service;

import java.util.List;

import com.joeun.board.dto.Board;
import com.joeun.board.dto.Page;

public interface BoardService {

    // 게시글 목록
    public List<Board> list() throws Exception;
    // 게시글 조회
    public Board select(int no) throws Exception;
    // 게시글 등록
    public int insert(Board board) throws Exception;
    // 게시글 수정
    public int update(Board board) throws Exception;
    // 게시글 삭제
    public int delete(int no) throws Exception;
    // 조회수 수정
    public int goodUpdate(Board board) throws Exception;
    // 페이지 네이션
    public Page calculatePageInformation(int totalItems, int pageSize, int currentPage, int visiblePages); 

    public int countEntities();

    public List<Board> findEntitiesWithPaging(int page, int pageSize) ;
}
