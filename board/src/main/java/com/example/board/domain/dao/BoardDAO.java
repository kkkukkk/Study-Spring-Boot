package com.example.board.domain.dao;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.PageDTO;
import com.example.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

//mapper를 바로 쓰지 않고 DAO를 거쳐서 필터링을 하자
@RequiredArgsConstructor
@Repository //DAO에는 Repository를 붙임
public class BoardDAO {
    private final BoardMapper boardMapper;

    public List<BoardVO> getList(Criteria criteria){
        return boardMapper.getList(criteria);
    }
    public void register(BoardVO boardVO){
        boardMapper.insert(boardVO);
    }
    public BoardVO read(long boardBno){
        return boardMapper.getDetail(boardBno);
    }
    public boolean remove(long boardBno){
        return boardMapper.delete(boardBno) != 0;
    }
    public boolean modify(BoardVO boardVO){
        return boardMapper.update(boardVO) != 0;
    }
    public int getTotal(Criteria criteria){return boardMapper.getTotal(criteria);}
}


