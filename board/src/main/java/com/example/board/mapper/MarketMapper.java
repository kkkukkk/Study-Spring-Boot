package com.example.board.mapper;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.MarketVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MarketMapper {

    //등록
    public void insert(MarketVO marketVO);
    //정보수정
    public boolean update(MarketVO marketVO);
    //삭제
    public boolean delete(Long marketNumber);
    //상세
    public MarketVO detail(Long marketNumber);
    //전체 읽어오기
    public List<MarketVO> getList(Criteria criteria);
}
