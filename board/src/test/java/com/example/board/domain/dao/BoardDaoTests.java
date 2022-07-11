package com.example.board.domain.dao;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.PageDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.security.auth.login.CredentialException;

@SpringBootTest
@Slf4j
public class BoardDaoTests {
    @Autowired
    private BoardDAO boardDAO;

//    @Test
//    public void getListTest(){
////        boardDAO.getList().forEach(t->{});
//        int total = boardDAO.getTotal();
//        boardDAO.getList(new Criteria()).stream().map(BoardVO::toString).forEach(log::info);
//    }

    @Test
    public void registerTest(){

        BoardVO boardVO = new BoardVO();

        boardVO.setBoardTitle("안녕하세요");
        boardVO.setBoardContent("반갑습니다");
        boardVO.setBoardWriter("박욱진");

        boardDAO.register(boardVO);

        log.info("게시글 번호 : " + boardVO.getBoardBno());
    }

    @Test
    public void readTest(){
        long boardBno = 3L;
        BoardVO detail = boardDAO.read(boardBno);
        log.info(detail.toString());
    }

    @Test
    public void removeTest(){
        long boardBno = 1L;
        log.info("Delete Count : " + boardDAO.remove(boardBno));
    }

    @Test
    public void modifyTest(){
        long boardBno = 3L;
        BoardVO boardVO = boardDAO.read(boardBno);

        if(boardVO == null) { log.info("NO BOARD"); return;}

        boardVO.setBoardTitle("반갑");
        boardVO.setBoardContent("습니");
        boardVO.setBoardWriter("다");

        log.info("UPDATE COUNT : " + boardDAO.modify(boardVO));

    }

//    @Test
//    public void getTotalTest(){
//        log.info("전체 개수 : " + boardDAO.getTotal());
//    }
}
