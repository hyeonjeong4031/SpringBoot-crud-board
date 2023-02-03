package com.crud.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.crud.board.entity.Board;
import com.crud.board.service.BoardService;

//anotation - Controller 에 대한 자동완성을 해준다.
@Controller
public class BoarderController {

    @Autowired
    private BoardService boardService;
  
    @GetMapping("/board/write") // localhost:8080/board/write 접근 시 html retrun
    public String boardWriteForm(){

        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board){

        //  controller 입장에서 boardService가 어떤건지 알 수 없어서 private로 init
        boardService.write(board);
        
        return "";
    }

    @GetMapping("/board/list")
    public String boardList(Model model){

        //모델로 데이터 받아옴
        // list라는 이름으로 데이터를 보낼 것 인데
        // boardService.boardList()실행 후 반환된 리스트가 보내진다.
        model.addAttribute("list", boardService.boardList());
        return "boardlist";
    }
    
}

