package com.crud.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

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
    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception{

        //  controller 입장에서 boardService가 어떤건지 알 수 없어서 private로 init
        boardService.write(board, file);
        
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");
        
        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model, @PageableDefault(page=0, size=10, sort="id", direction=Sort.Direction.DESC) Pageable pageable){

        //모델로 데이터 받아옴
        // list라는 이름으로 데이터를 보낼 것 인데
        // boardService.boardList()실행 후 반환된 리스트가 보내진다.
        model.addAttribute("list", boardService.boardList(pageable));
        return "boardlist";
    }

    @GetMapping("/board/view") //localhost:8080/board/view?id=1
    public String boardView(Model model, Integer id){

        model.addAttribute("board", boardService.boardView(id));

        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id){

        boardService.boardDelete(id);

        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id, Model model){
        
        System.out.println("여기 찍히나??????");
        model.addAttribute("board", boardService.boardView(id));
        
        return "boardmodify";
    }
    
    
    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, MultipartFile file) throws Exception{
        
        System.out.println("post !!!!!!mapping??????");

        // 기존에 있던 내용(객체) 가져오기
        Board boardTemp = boardService.boardView(id);
        //  새로 가져온 내용(Board 객체)으로 덮어쓰기
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp, file);


        // model.addAttribute("message", "글 작성이 완료되었습니다.");
   
        // model.addAttribute("searchUrl", "/board/list");


        return "redirect:/board/list";
    }
}

