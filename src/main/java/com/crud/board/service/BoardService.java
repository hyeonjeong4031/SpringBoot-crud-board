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

    public void write(Board board){
        boardRepository.save(board);
    }

    public List<Board> boardList(){
        return boardRepository.findAll();
    }
}
