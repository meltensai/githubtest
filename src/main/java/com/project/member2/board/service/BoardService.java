package com.project.member2.board.service;

import com.project.member2.board.dto.BoardDTO;
import com.project.member2.board.entity.BoardEntity;
import com.project.member2.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Transactional //JPA의 함수 아니라 본인이 지정한 함수 사용 시 Transactional 어노테이션 사용 必
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
//       BoardDTO boardDTO = BoardDTO.toBoardDTO(boardRepository.findById(id).get());
//       return boardDTO;
    }

    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toupdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }

    public void boardDelete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber()-1;
        int pageLimit = 3; //한 페이지에 보여줄 글 갯수
        Page<BoardEntity> boardEntities =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));
    }
}