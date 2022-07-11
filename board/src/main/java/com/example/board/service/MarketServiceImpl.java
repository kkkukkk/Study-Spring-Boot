package com.example.board.service;

import com.example.board.domain.dao.MarketDAO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.MarketVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService{
    public final MarketDAO marketDAO;

    @Override
    public void register(MarketVO marketVO) {
        marketDAO.register(marketVO);
    }

    @Override
    public MarketVO read(Long marketNumber) {
        return marketDAO.read(marketNumber);
    }

    @Override
    public boolean modify(MarketVO marketVO) {
        return marketDAO.modify(marketVO);
    }

    @Override
    public boolean remove(Long marketNumber) {
        return marketDAO.remove(marketNumber);
    }

    @Override
    public List<MarketVO> getList(Criteria criteria) {
        return marketDAO.getList(criteria);
    }

}
