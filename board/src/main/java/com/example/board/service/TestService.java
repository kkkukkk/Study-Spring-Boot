package com.example.board.service;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.TestVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TestService {
    public void register(TestVO testVO);
    public TestVO read(Long testNumber);
    public boolean modify(TestVO testVO);
    public boolean remove(Long testNumber);
    public List<TestVO> getList(Criteria criteria);
}
