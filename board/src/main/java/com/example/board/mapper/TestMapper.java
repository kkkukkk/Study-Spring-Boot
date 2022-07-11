package com.example.board.mapper;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.TestVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    //테스트 게시물 추가
    public void insert(TestVO testVO);

    //테스트 게시물 읽기
    public TestVO detail(Long testNumber);

    //테스트 게시물 삭제
    public boolean delete(Long testNumber);

    //테스트 게시물 수정
    public boolean update(TestVO testVO);

    //테스트 게시물 리스트
    public List<TestVO> getList(Criteria criteria);




}
