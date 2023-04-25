package com.project.member2.board.Controller;

import com.project.member2.board.dto.BoardDTO;
import com.project.member2.board.service.BoardService;
import lombok.RequiredArgsConstructor;
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
}
