package com.example.board.domain.dao;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReplyDaoTests {
    @Autowired
    private ReplyDAO replyDAO;

    @Test
    public void insertTest(){

        ReplyVO replyVO = new ReplyVO();

        replyVO.setBoardBno((long) 45279);
        replyVO.setReplyContent("안녕하세요");
        replyVO.setReplyWriter("박욱진");

        replyDAO.comment(replyVO);

        log.info("게시글 번호 : " + replyVO.getBoardBno());
        log.info("댓글 번호 : " + replyVO.getReplyNumber());
    }

    @Test
    public void getDetailTest(){
        long replyNumber = 1L;
        ReplyVO detail = replyDAO.readOne(replyNumber);
        log.info(detail.toString());
    }

    @Test
    public void deleteTest(){
        log.info("DELETE : " + replyDAO.remove(1l));
    }

    @Test
    public void updateTest(){
        ReplyVO replyVO = new ReplyVO();
        replyVO.setReplyNumber(2L);
        replyVO.setReplyContent("수정된 댓글n");
        log.info("UPDATE : " + replyDAO.modify(replyVO));

    }

    @Test
    public void getListTest(){
        replyDAO.getList(new Criteria(), 45277L).stream().map(ReplyVO::toString).forEach(log::info);
    }

    @Test
    public void getTotalTest(){
        log.info("댓글 개수 : " + replyDAO.getTotal(77829L));
    }

}
