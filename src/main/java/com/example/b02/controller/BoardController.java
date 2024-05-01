package com.example.b02.controller;

import com.example.b02.dto.BoardDTO;
import com.example.b02.entity.Board;
import com.example.b02.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/register")
    public void register(){
        log.info("get register....");
    }
    @PostMapping("/register")
    public String registerPost(BoardDTO boardDTO){
        log.info("post register....");
        boardService.register(boardDTO);
        return "redirect:list";
    }
    @GetMapping("/list")
    public void list(Model model){
        List<Board> boardList = boardService.select();
        model.addAttribute("list", boardList);
    }
    @GetMapping({"/read", "/modify"})
    public void read(Long bno, Model model){
        log.info(bno);
       BoardDTO boardDTO = boardService.read(bno);
        log.info(bno);
        model.addAttribute("dto",boardDTO);
    }
    @PostMapping("/modify")
    public String modify(BoardDTO boardDTO, RedirectAttributes redirectAttributes){
        //boardService.modify(boardDTO);
        return null;
    }
    @PostMapping("/remove")
    public void remove(Long bno){
        boardService.remove(bno);
    }
}
