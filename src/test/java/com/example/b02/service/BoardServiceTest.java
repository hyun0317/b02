package com.example.b02.service;


import com.example.b02.dto.BoardDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    public void register(){
        BoardDTO boardDTO = BoardDTO.builder()
                .title("제목-1")
                .content("내용-1")
                .writer("작성자-1")
                .build();

        Long bno = boardService.register(boardDTO);

        log.info(bno);
    }

    @Test
    public void readTest(){

        BoardDTO boardDTO = boardService.read(250L);

        log.info(boardDTO);

    }

}
