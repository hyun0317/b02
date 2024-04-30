package com.example.b02.service;

import com.example.b02.dto.BoardDTO;
import com.example.b02.entity.Board;
import com.example.b02.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    @Override
    public Long register(BoardDTO boardDTO) {

        Board board = modelMapper.map(boardDTO, Board.class);

        Long bno = boardRepository.save(board).getBno();

        return bno;

    }

    @Override
    public List<Board> select() {
        return boardRepository.findAll();
    }

    @Override
    public BoardDTO read(Long bno) {
        Optional<Board> board =
                boardRepository.findById(bno);
        BoardDTO boardDTO = modelMapper.map(board.get(),BoardDTO.class);
        return boardDTO;
    }

    @Override
    public void modify(Board board) {
        boardRepository.save(board);
    }

    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
    }
}
