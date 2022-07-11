package com.example.board.mapper;


import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    //댓글 추가
    public void insert(ReplyVO replyVO);

    //댓글 1개 만 조회
    public ReplyVO getDetail(Long replyNumber);

    //댓글 삭제
    public int delete(Long replyNumber);

    //댓글 수정
    public int update(ReplyVO replyVO);

    //댓글 목록
    //@Param 어노테이션을 붙이면 myBatis 쪽에서 해당 키값으로 값을 사용할 수 있음
    //해시맵으로 담아서 사용하는 것 대신 사용
    public List<ReplyVO> getList(@Param("criteria") Criteria criteria, @Param("boardBno") Long boardBno);

    //댓글 개수
    public int getTotal(Long boardBno);
}
