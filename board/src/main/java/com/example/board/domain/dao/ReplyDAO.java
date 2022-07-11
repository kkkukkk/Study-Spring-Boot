package com.example.board.domain.dao;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import com.example.board.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
@Slf4j
public class ReplyDAO {
    private final ReplyMapper replyMapper;

    public void comment(ReplyVO replyVO){
        log.info("register..... : " + replyVO);
        replyMapper.insert(replyVO);
    }

    public ReplyVO readOne(Long replyNumber){
        log.info("read...... : " + replyNumber);
        return replyMapper.getDetail(replyNumber);
    }

    public boolean remove(Long replyNumber){
        log.info("remove...... : " + replyNumber);
        return replyMapper.delete(replyNumber) == 1;
    }

    public boolean modify(ReplyVO replyVO){
        log.info("modify........ : " + replyVO);
        return replyMapper.update(replyVO) == 1;
    }

    public List<ReplyVO> getList(Criteria criteria, Long boardBno){
        log.info("getList........ : " + criteria);
        log.info("getList........ : " + boardBno);
        return replyMapper.getList(criteria, boardBno);
    }

    //댓글 개수
    public int getTotal(Long boardBno){
        return replyMapper.getTotal(boardBno);
    }
}
