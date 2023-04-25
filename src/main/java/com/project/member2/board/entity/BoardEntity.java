package com.project.member2.board.entity;

//DB의 테이블 역할을 하는 클래스

import com.project.member2.board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board_table") //사용하면 자체적으로 db에 table만들 필요 없어짐
public class BoardEntity extends BaseEntity {
    @Id //pk값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id값생성될떄마다 자동 증가(auto_increment)
    private Long id;

    @Column(length = 20, nullable = false) //크기 20, not null
    private String boardWriter;

    @Column
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContent;

    @Column
    private int boardHits;

    public static BoardEntity tosaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }
}
