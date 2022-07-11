package com.example.board.domain.dao;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.MarketVO;
import com.example.board.mapper.MarketMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MarketDAO {
    public final MarketMapper marketMapper;

    public void register(MarketVO marketVO){
        marketMapper.insert(marketVO);
    }

    public boolean remove(Long marketNumber){
        return marketMapper.delete(marketNumber);
    }

    public boolean modify(MarketVO marketVO){
        return marketMapper.update(marketVO);
    }

    public MarketVO read(Long marketNumber){
        return marketMapper.detail(marketNumber);
    }

    public List<MarketVO> getList(Criteria criteria){
        return marketMapper.getList(criteria);
    }

}
