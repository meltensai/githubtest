package com.project.member2.board.Controller;

import com.project.member2.board.dto.BoardDTO;
import com.project.member2.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board") //모든 getmapping에 /board 가 기본으로 깔림
public class b_MainController {
    private final BoardService boardService;

    @GetMapping("/write")
    public String writeForm() {
        return "board_write";
    }

    @PostMapping("/write") //RequestParam을 통해 각각 값의 name을 괄호 안에 넣고, String 값으로 받기
    public String write(@ModelAttribute BoardDTO boardDTO) {
        boardService.save(boardDTO);
        return "board_index";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        //DTO를 전부 다 list 형태로 받음
        List<BoardDTO> boardDTOList = boardService.findAll();
        //받은 list값을 model로 변환("boardList"라는 이름으로..)
        model.addAttribute("boardList", boardDTOList);
        //"board_list" html에서 보여줌
        return "board_list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        //실행 시 해당 게시물의 조회수를 하나 올리고 게시글 데이터를 가져와서 "detail" 에 출력
        boardService.updateHits(id);
        model.addAttribute("board", boardService.findById(id));
        return "board_detail";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        model.addAttribute("boardUpdate", boardService.findById(id));
        return "board_update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        String temp = boardService.update(boardDTO).getId().toString();
        model.addAttribute("board", boardService.findById(boardDTO.getId()));
        return "redirect:/board/"+temp;
    }

    @GetMapping("/delete/{id}") //modelattribute는 왜 안될까?
    public String delete(@PathVariable Long id) {
        boardService.boardDelete(id);
        return "redirect:/board/list";
    }

    // /board/paging?page=1 와 같이 설정
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        pageable.getPageNumber();
        Page<BoardDTO> boardList = boardService.paging(pageable);
    }
}
