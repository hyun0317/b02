package com.example.b02.service;

import com.example.b02.dto.BoardDTO;
import com.example.b02.dto.PageRequestDTO;
import com.example.b02.dto.PageResponseDTO;
import com.example.b02.entity.Board;
import com.example.b02.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();

        String keyword = pageRequestDTO.getKeyword();

        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> boardPage = boardRepository.searchAll(types,keyword,pageable);

        List<BoardDTO> boardDTOS =
        boardPage.getContent().stream()
                .map(board -> modelMapper.map(board,BoardDTO.class))
                .collect(Collectors.toList());

//        PageResponseDTO<BoardDTO> aa =
//                new PageResponseDTO<BoardDTO>(pageRequestDTO,boardDTOS,(int) boardPage.getTotalElements());

//        return aa;

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(boardDTOS)
                .total((int)boardPage.getTotalElements())
                .build();
    }

    @Override
    public BoardDTO read(Long bno) {
        Optional<Board> board =
                boardRepository.findById(bno);
        log.info("값 확인" + board.isEmpty());

        if (board == null){
            return null;
        }

        BoardDTO boardDTO = modelMapper.map(board.orElseThrow(),BoardDTO.class);

        return boardDTO;
    }

    @Override
    public void modify(BoardDTO boardDTO) {

        Optional<Board> result = boardRepository.findById(boardDTO.getBno());

        Board board = result.orElseThrow();

        board.chang(boardDTO.getTitle(), boardDTO.getContent());

        boardRepository.save(board);

//        Board board = modelMapper.map(boardDTO,Board.class);
//
//        boardRepository.save(board);
    }

    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
    }
}
