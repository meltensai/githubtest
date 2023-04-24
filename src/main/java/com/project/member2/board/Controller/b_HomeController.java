package com.project.member2.board.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class b_HomeController {
    @GetMapping("/board")
    public String b_index() {
        return "board_index";
    }
}
