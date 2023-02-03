package com.crud.board.repository;

import com.crud.board.entity.Board;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


 // <entity 이름, primary key 기본타입>
@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{}