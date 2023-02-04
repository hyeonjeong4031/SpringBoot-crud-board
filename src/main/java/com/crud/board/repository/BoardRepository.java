package com.crud.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.board.entity.Board;


 // <entity 이름, primary key 기본타입>
@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{

    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}