package com.study.app.controller;

import com.study.app.dto.BoardDto;
import com.study.app.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import security.BoardPrincipal;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board")
    public String board(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            @AuthenticationPrincipal BoardPrincipal boardPrincipal,
            Model model) {
        Page<BoardDto> board = boardService.getBoardList(pageable);
System.out.println("Board 값 :" + board.toString());
System.out.println("Board 값 :" + board.getTotalElements());
System.out.println("Board 값 :" + board);
        model.addAttribute("board", board);
        model.addAttribute("principal",boardPrincipal);
        return "index";
    }
}
