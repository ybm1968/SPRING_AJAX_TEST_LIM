package com.joeun.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joeun.board.dto.Board;
import com.joeun.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/good")
public class GoodController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/{boardNo}")
    public ResponseEntity<?> getOne(@PathVariable Integer boardNo) {
        try {
            Board board = boardService.select(boardNo);
             if(board == null) {
                log.info("조회된 좋아요 없음");
            }
            
            return new ResponseEntity<>(board, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{boardNo}")
    public ResponseEntity<?> update(@PathVariable Integer boardNo) {
        log.info("[PUT] - / 좋아요 수정");
        log.info("boardNo : " + boardNo);
        try {
            Board board = boardService.select(boardNo);
            log.info("board : " + board);
            int goodCount = board.getGood() + 1;
            board.setGood(goodCount);
            int result = boardService.update(board);
            log.info("result : " + result);
            
            if( result > 0 )
                return new ResponseEntity<>("success", HttpStatus.OK); 
            else 
                return new ResponseEntity<>("failed", HttpStatus.OK);
        } catch (Exception e) {
            log.error(null, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}








