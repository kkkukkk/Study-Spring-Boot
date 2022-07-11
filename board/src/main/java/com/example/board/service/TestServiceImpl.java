package com.example.board.service;

import com.example.board.domain.dao.TestDAO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.TestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TestServiceImpl implements TestService{
    public final TestDAO testDAO;

    @Override
    public void register(TestVO testVO) {
        testDAO.register(testVO);
    }

    @Override
    public TestVO read(Long testNumber) {
        return testDAO.read(testNumber);
    }

    @Override
    public boolean modify(TestVO testVO) {
        return testDAO.modify(testVO);
    }

    @Override
    public boolean remove(Long testNumber) {
        return testDAO.remove(testNumber);
    }

    @Override
    public List<TestVO> getList(Criteria criteria) {
        return testDAO.getList(criteria);
    }
}
