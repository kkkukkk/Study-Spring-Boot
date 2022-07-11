package com.example.board.service;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.MarketVO;

import java.util.List;

public interface MarketService {
    public void register(MarketVO marketVO);
    public MarketVO read(Long marketNumber);
    public boolean modify(MarketVO marketVO);
    public boolean remove(Long marketNumber);
    public List<MarketVO> getList(Criteria criteria);
}
