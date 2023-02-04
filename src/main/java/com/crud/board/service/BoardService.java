package com.crud.board.service;
import  java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crud.board.entity.Board;
import com.crud.board.repository.BoardRepository;

@Service
public class BoardService {
    // 원래 interface라서 객체 생성하려면 new연산자 사용해야 하지만
    // spring bean이 알아서  읽어와서 private에 주입해준다 (DI)
    @Autowired
    private BoardRepository boardRepository;

    // 글 작성 처리
    public void write(Board board, MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir")+"/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);
        file.transferTo(saveFile);

        board.setFilename(fileName);
        // 서버에서 접근 시, static 밑에 있는 경로부터 접근 가능
        board.setFilepath("/files/" + fileName);

        boardRepository.save(board);
    }

    // 게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
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
