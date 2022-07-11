package com.example.board.domain.dao;


import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.TestVO;
import com.example.board.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TestDAO {
    private final TestMapper testMapper;

    public List<TestVO> getList(Criteria criteria){
        return testMapper.getList(criteria);
    }
    public void register(TestVO testVO){
        testMapper.insert(testVO);
    }
    public TestVO read(long testNumber){
        return testMapper.detail(testNumber);
    }
    public boolean remove(long testNumber){
        return testMapper.delete(testNumber);
    }
    public boolean modify(TestVO testVO){
        return testMapper.update(testVO);
    }
}
