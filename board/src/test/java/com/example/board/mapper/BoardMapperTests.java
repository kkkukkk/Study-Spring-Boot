package com.example.board.mapper;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardMapperTests {
    @Autowired
    private BoardMapper boardMapper;

//    @Test
//    public void getListTest(){
////        boardMapper.getList().forEach(t->{});
//        int total = boardMapper.getTotal();
//        boardMapper.getList(new Criteria()).stream().map(BoardVO::toString).forEach(log::info);
//
//    }

    @Test
    public void insertTest(){

        BoardVO boardVO = new BoardVO();

        boardVO.setBoardTitle("안녕하세요");
        boardVO.setBoardContent("반갑습니다");
        boardVO.setBoardWriter("박욱진");

        boardMapper.insert(boardVO);

        log.info("게시글 번호 : " + boardVO.getBoardBno());
    }

    @Test
    public void getDetailTest(){
        long boardBno = 3L;
        BoardVO detail = boardMapper.getDetail(boardBno);
        log.info(detail.toString());
    }

    @Test
    public void deleteTest(){
        long boardBno = 1L;

        log.info("Delete Count : " + boardMapper.delete(boardBno));
    }

    @Test
    public void updateTest(){
        long boardBno = 3L;
        BoardVO boardVO = boardMapper.getDetail(boardBno);

        if(boardVO == null) { log.info("NO BOARD"); return;}

        boardVO.setBoardTitle("반갑");
        boardVO.setBoardContent("습니");
        boardVO.setBoardWriter("다");

        log.info("UPDATE COUNT : " + boardMapper.update(boardVO));

    }

//    @Test
//    public void getTotalTest(){
//        log.info("전체 게시글 수 : " + boardMapper.getTotal());
//    }


}
