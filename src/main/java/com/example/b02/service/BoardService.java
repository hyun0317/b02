package com.example.b02.service;

import com.example.b02.dto.BoardDTO;
import com.example.b02.entity.Board;

import java.util.List;

public interface BoardService {
    public Long register(BoardDTO boardDTO);

    public List<Board> select();

    //페이징처리, 검색처리, 목록

    public BoardDTO read(Long bno);

    public void modify(BoardDTO boardDTO);

    public void remove(Long bno);
}
