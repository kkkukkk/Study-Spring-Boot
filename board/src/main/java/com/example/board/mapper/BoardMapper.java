package com.example.board.mapper;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
//    게시글 목록
    public List<BoardVO> getList(Criteria criteria);
//    게시글 등록
    public void insert(BoardVO boardVO);
//    게시글 상세보기
    public BoardVO getDetail(long boardBno);
//    게시글 삭제
    public int delete(long boardBno);
//    게시글 수정
    public int update(BoardVO boardVO);
//    게시글 전체 개수
    public int getTotal(Criteria criteria);


    // insert, update, delete는 기본적으로 실행된 건 수를 가져오게됨
    // int로 리턴받을 수 있음
}
