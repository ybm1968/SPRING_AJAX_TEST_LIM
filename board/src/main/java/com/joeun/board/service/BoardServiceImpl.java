package com.joeun.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joeun.board.dto.Board;
import com.joeun.board.dto.Page;
import com.joeun.board.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<Board> list() throws Exception {
        List<Board> boardList = boardMapper.list();
        return boardList;
    }

    @Override
    public Board select(int no) throws Exception {
        Board board = boardMapper.select(no);
        return board;
    }

    @Override
    public int insert(Board board) throws Exception {
        int result = boardMapper.insert(board);
        return result;
    }

    @Override
    public int update(Board board) throws Exception {
        int result = boardMapper.update(board);
        return result;
    }

    @Override
    public int delete(int no) throws Exception {
        int result = boardMapper.delete(no);
        return result;
    }

    @Override
    public int goodUpdate(Board board) throws Exception {
        int result = boardMapper.goodUpdate(board);
        return result;
    }

    @Override
    public Page calculatePageInformation(int totalItems, int pageSize, int currentPage, int visiblePages) {
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        // Ensure currentPage is within valid range
        currentPage = Math.max(1, Math.min(currentPage, totalPages));

        boolean hasPrevious = currentPage > 1;
        boolean hasNext = currentPage < totalPages;

        int startPage = Math.max(1, currentPage - visiblePages / 2);
        int endPage = Math.min(totalPages, startPage + visiblePages - 1);

        Page page = new Page();

        page.setTotalPages(totalPages);
        page.setCurrentPage(currentPage);
        page.setHasPrevious(hasPrevious);
        page.setHasNext(hasNext);
        page.setStartPage(startPage);
        page.setEndPage(endPage);

        return page;
    }

    public int countEntities() {
        return boardMapper.countEntities();
    }

    public List<Board> findEntitiesWithPaging(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return boardMapper.findEntitiesWithPaging(offset, pageSize);
    }
    
}
