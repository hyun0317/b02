package com.example.b02.repository.search;

import com.example.b02.entity.Board;
import com.example.b02.entity.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {


    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(Pageable pageable) {

        QBoard board = QBoard.board; //q도메인 객체

        JPQLQuery<Board> query = from(board);

        System.out.println("from(board) : " + query);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.or(board.title.contains("1"));
        booleanBuilder.or(board.content.contains("1"));
        booleanBuilder.or(board.writer.contains("1"));

        //query.where(board.title.contains("1"));
        query.where(booleanBuilder);
        System.out.println(query);
        query.where(board.bno.gt(0L));
        this.getQuerydsl().applyPagination(pageable, query);


        System.out.println("contains(\"1\") : " + query);

        List<Board> list = query.fetch();

        //System.out.println(list.get(0).getTitle());

        long count = query.fetchCount();

        System.out.println(count);

        return null;
    }

    @Override
    public Page<Board> searchAll(String[] types, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if ((types != null && types.length > 0) && keyword != null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for (String string : types){
                switch (string){
                    case "t":
                    booleanBuilder.or(board.title.contains(keyword));
                    break;
                    case "c":
                        booleanBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        booleanBuilder.or(board.writer.contains(keyword));
                        break;
                }

            }
            query.where(booleanBuilder);
            System.out.println("검색조건 추가:" + query);
        }
        query.where(board.bno.gt(0L));
        System.out.println("0보다 큰 조건 bno가" + query);

        //페이징
        this.getQuerydsl().applyPagination(pageable,query);
        List<Board> boardList = query.fetch();
        boardList.forEach(board1 ->log.info(board1));
        long count = query.fetchCount();

       return new PageImpl<>(boardList, pageable, count);
    }
}
