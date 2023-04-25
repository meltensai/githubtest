package com.project.member2.board.service;

import com.project.member2.board.dto.BoardDTO;
import com.project.member2.board.entity.BoardEntity;
import com.project.member2.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//boardRepository에서 save을 하려면 Entity값이 되어야 하기 때문에 dto -> entity로 변환하는 작업을 해야함

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.tosaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        //boardRepository.findALl()로 하면 entity 형태로 받게됨 -> DTO로 변환 필요
        List<BoardDTO> boardDTOList = new ArrayList<>();
        //변환할 List 새로 정의
        for(BoardEntity boardEntity: boardEntityList) {
            //boardEntityList의 boardEntity값이 toBoardDTO로 인해 DTO값으로 변환 후, boardDTOList에 삽입됨.
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }
}
