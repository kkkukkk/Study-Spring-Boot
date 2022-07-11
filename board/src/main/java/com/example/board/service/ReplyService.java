package com.example.board.service;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyService {
    public void comment(ReplyVO replyVO);
    public ReplyVO readOne(Long replyNumber);
    //댓글 삭제
    public boolean remove(Long replyNumber);

    //댓글 수정
    public boolean modify(ReplyVO replyVO);

    //댓글 목록
    //@Param 어노테이션을 붙이면 myBatis 쪽에서 해당 키값으로 값을 사용할 수 있음
    //해시맵으로 담아서 사용하는 것 대신 사용
    public List<ReplyVO> getList(@Param("criteria") Criteria criteria, @Param("boardBno") Long boardBno);

    public int getTotal(Long boardBno);
}
