package com.example.board.service;

import com.example.board.domain.dao.ReplyDAO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyServiceImpl implements ReplyService{
    public final ReplyDAO replyDAO;

    @Override
    public void comment(ReplyVO replyVO) {
        replyDAO.comment(replyVO);
    }

    @Override
    public ReplyVO readOne(Long replyNumber) {
        return replyDAO.readOne(replyNumber);
    }

    @Override
    public boolean remove(Long replyNumber){
        log.info("remove...... : " + replyNumber);
        return replyDAO.remove(replyNumber);
    }

    @Override
    public boolean modify(ReplyVO replyVO){
        log.info("modify........ : " + replyVO);
        return replyDAO.modify(replyVO);
    }

    @Override
    public List<ReplyVO> getList(Criteria criteria, Long boardBno){
        log.info("getList........ : " + criteria);
        log.info("getList........ : " + boardBno);
        return replyDAO.getList(criteria, boardBno);
    }

    @Override
    public int getTotal(Long boardBno) {
        return replyDAO.getTotal(boardBno);
    }


}
