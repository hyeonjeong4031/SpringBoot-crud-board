package com.crud.board.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// entity = db에 있는 table을 의미한다.
// JPA 가 해당 필드를 읽는다
// lombok- DATA anotation -- entity를 읽고 싶지만 읽지 못하기 때문에 사용
@Entity
@Data
public class Board {

    @Id // 기본키 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 키값의 자동생성 전략 사용
    private Integer id;

    private String title;

    private String content;

    private String filename;

    private String filepath;
}
