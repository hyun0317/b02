package com.example.b02.service;

import com.example.b02.dto.BoardDTO;
import com.example.b02.entity.Board;

import java.util.List;

public interface BoardService {
    public Long register(BoardDTO boardDTO);

    public List<Board> select();

    public BoardDTO read(Long bno);

    public void modify(Board board);

    public void remove(Long bno);
}
