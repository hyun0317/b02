package com.example.b02.repository;

import com.example.b02.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
public class BoardRepositryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public  void  searchTest(){
        Pageable pageable = PageRequest.of(0,10);
        boardRepository.search1(pageable);

    }

    @Test
    public void searchAll(){
        String[] strs = {"t","c"};

        String keyword = "용3";
        Pageable pageable = PageRequest.of(0,7, Sort.by("bno").descending());

        Page<Board> boards = boardRepository.searchAll(strs, keyword, pageable);

        boards.forEach(board -> System.out.println(board));
        System.out.println("총 페이지" + boards.getTotalPages());
        System.out.println("사이즈" + boards.getSize());
        System.out.println("페이지번호" + boards.getNumber());
        System.out.println("시작 페이지" + boards.isFirst());
        System.out.println("다음 페이지" + boards.hasNext());

    }

    @Test
    public void savetest(){

        for(int i = 0; i< 500; i++){
            Board board = Board.builder()
                    .title("제목" + i)
                    .content("내용"+ i)
                    .writer("작성자" + i)
                    .build();
            boardRepository.save(board);
        }

    }

}
