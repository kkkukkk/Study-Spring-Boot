package com.example.board.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

//Criteria : 검색의 기준
@Component
@Data
@AllArgsConstructor
public class Criteria {
    private int pageNum;
    private int amount;
    private String type;
    private String keyword;

    // 생성자에 별다른 값을 넣지 않았을 경우 기본으로
    // pageNum = 1, amount = 10을 넣어준다.
    public Criteria() {
        this(1,10);
    }

    // 전달받을 값이 4개가 되었으므로 2개짜리 생성자를 만들어줌
    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public String getListLink() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum", this.pageNum)
                .queryParam("amount", this.amount)
                .queryParam("type", this.type)
                .queryParam("keyword", this.keyword);

        return builder.toUriString();
    }

    public String[] getTypes(){
        return type == null ? new String[] {} : type.split("");
    }


}

