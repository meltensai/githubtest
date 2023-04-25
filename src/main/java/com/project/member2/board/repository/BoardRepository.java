package com.project.member2.board.repository;

import com.project.member2.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    //<>안에 Entity 클래스 이름, pk 이름 적으면 됨
    //update board_table set board_hits = board_hits+1 where id = ? << query문 작성 필요
    @Modifying //Query update할때 필수로 붙이기
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id=:id3")
    void updateHits(@Param("id3") long id);

}
