package com.crud.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.board.entity.Board;
import com.crud.board.repository.BoardRepository;

@Service
public class BoardService {
    // 원래 interface라서 객체 생성하려면 new연산자 사용해야 하지만
    // spring bean이 알아서  읽어와서 private에 주입해준다 (DI)
    @Autowired
    private BoardRepository boardRepository;

    // 글 작성 처리
    public void write(Board board){
        boardRepository.save(board);
    }

    // 게시글 리스트 처리
    public List<Board> boardList(){
        return boardRepository.findAll();
    }

    // 상세 게시글 불러오기
    public Board boardView(Integer id){
        //  findby id를 optional 값으로 받아오는데 get으로 해주면 된다
        return boardRepository.findById(id).get();
    }

    // 특정 게시글 삭제
    public void boardDelete(Integer id){
        
        boardRepository.deleteById(id);
    }
}
