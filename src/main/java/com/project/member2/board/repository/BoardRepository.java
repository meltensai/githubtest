package com.project.member2.board.repository;

import com.project.member2.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    //<>안에 Entity 클래스 이름, pk 이름 적으면 됨
}
