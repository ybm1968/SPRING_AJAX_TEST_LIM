package com.joeun.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joeun.board.dto.Board;
import com.joeun.board.dto.Page;
import com.joeun.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;



 

/**
 *  게시판 컨트롤러
 * - 게시글 목록            - [GET] - /board/list
 * - 게시글 조회            - [GET] - /board/read
 * - 게시글 등록            - [GET] - /board/insert
 * - 게시글 등록 처리       - [POST] - /board/insert
 * - 게시글 수정            - [GET] - /board/update
 * - 게시글 수정 처리       - [POST] - /board/update
 * - 게시글 삭제 처리       - [POST] - /board/delete
 */
@Slf4j              // 로그 사용 어노테이션
@Controller
@RequestMapping("/board")
public class BoardController {
    
    // 한꺼번에 import : alt + shift + O

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "5") int pageSize,
                              Model model) {
        log.info("page : " + page);
        log.info("pageSize : " + pageSize);
        int totalItems = boardService.countEntities(); // Implement this method in your service
        List<Board> boardList = boardService.findEntitiesWithPaging(page, pageSize);
    
        int visiblePages = 10;
    
        Page pageInfo = boardService.calculatePageInformation(totalItems, pageSize, page, visiblePages);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageInfo", pageInfo);
    
        return "board/list";
    }

    /**
     * 게시글 조회
     * [GET] 
     * /board/read
     * - model : board, fileList
     * @param model
     * @param boardNo
     * @return
     * @throws Exception
     */
    @GetMapping(value="/read")
    public String read(Model model, int boardNo) throws Exception {
        log.info("[GET] - /board/read");

        // 데이터 요청
        Board board = boardService.select(boardNo);     // 게시글 정보

        // 모델 등록
        model.addAttribute("board", board);

        // 뷰 페이지 지정
        return "board/read";
    }


    /**
     * 게시글 쓰기
     * [GET]
     * /board/insert
     * model : ❌ 
     * @return
     */
    @GetMapping(value="/insert")
    public String insert(@ModelAttribute Board board) {
        return "board/insert";
    }
    
    /**
     * 게시글 쓰기 처리
     * [POST]
     * /board/insert
     * model : ❌
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping(value="/insert")
    public String insertPro(@ModelAttribute Board board) throws Exception {
        // @ModelAttribute : 모델에 자동으로 등록해주는 어노테이션
        // 데이터 처리
        int result = boardService.insert(board);

        // 게시글 쓰기 실패 ➡ 게시글 쓰기 화면
        if( result == 0 ) return "board/insert";

        // 뷰 페이지 지정
        return "redirect:/board/list";
    }
    
    /**
     * 게시글 수정
     * [GET]
     * /board/update
     * model : board
     * @param model
     * @param boardNo
     * @return
     * @throws Exception
     */
    @GetMapping(value="/update")
    public String update(Model model, int boardNo) throws Exception {
        // 데이터 요청
        Board board = boardService.select(boardNo);
        // 모델 등록
        model.addAttribute("board", board);
        // 뷰 페이지 지정
        return "board/update";
    }
    
    /**
     * 게시글 수정 처리
     * [POST]
     * /board/update
     * model : board
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping(value="/update")
    public String updatePro(Board board) throws Exception {
        // 데이터 처리
        int result = boardService.update(board);
        int boardNo = board.getBoardNo();

        // 게시글 수정 실패 ➡ 게시글 수정 화면
        if( result == 0 ) return "redirect:/board/update?boardNo=" + boardNo;
        
        // 뷰 페이지 지정
        return "redirect:/board/list";
    }

    /**
     * 게시글 삭제 처리
     * [POST]
     * /board/delete
     * model : ❌
     * @param boardNo
     * @return
     * @throws Exception
     */
    @PostMapping(value="/delete")
    public String deletePro(int boardNo) throws Exception {
        // 데이터 처리
        int result = boardService.delete(boardNo);
        
        // 게시글 삭제 실패 ➡ 게시글 수정 화면
        if( result == 0 ) return "redirect:/board/update?boardNo=" + boardNo;

        // 뷰 페이지 지정
        return "redirect:/board/list";
    }


}
