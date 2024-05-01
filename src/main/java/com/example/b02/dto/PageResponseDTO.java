package com.example.b02.dto;


import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {

    private int page;

    private int size;

    private int total; //게시물 총수, 검색시 총수량이 변경됨

    // 시작 페이지번호
    private int start;
    // 끝 페이지번호
    private int end;
    // 이전 페이지 존재 여부
    private boolean prev;
    // 다음 페이지 존재 여부
    private boolean next;

    private List<E> dtoList;  //목록에 대한 결과값, 제너럴 사용으로 다른곳에서도 사용이 가능함

}
