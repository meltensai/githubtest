package com.project.member2.board.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board") //모든 getmapping에 /board 가 기본으로 깔림
public class b_MainController {
    @GetMapping("/write")
    public String writeForm() {
        return "board_write";
    }
}
