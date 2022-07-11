package com.example.board.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class PageDTO {
    private Criteria criteria;

    private int startPage;
    private int endPage;
    private int realEnd;
    private int pageCount;
    private boolean prev, next ;
    private int total;


    // pageCount를 따로 정해주지 않았을 경우
    // 기본적으로 10을 넣어준다.
    public PageDTO(Criteria criteria, int total) {
        this(criteria, 10, total);
    }

    public PageDTO(Criteria criteria, int pageCount, int total){
        this.criteria = criteria;
        this.total = total;
        // pageNum을 page개수로 나눈 값을 올림한 뒤 page개수를 곱한 것을 endPage로 한다.
        // 한번에 보여줄 페이지 수 중 마지막 페이지에 해당하는 번호
        this.endPage = (int)Math.ceil(criteria.getPageNum() / (double)pageCount) * pageCount;
        // 시작 페이지는 endPage에서 page개수를 뺀 뒤 1을 더해준 값이다.
        this.startPage = this.endPage - pageCount + 1;
        // 전체 페이지 수를 페이지당 개수로 나눈 것을 올림한 값을 실제 마지막 페이지로 사용한다.
        // 실제 게시글 리스트의 마지막 페이지
        this.realEnd = (int)Math.ceil((double)total / criteria.getAmount());

        // 실제 마지막 페이지가 endPage보다 작다면 endPage는 realEnd 페이지가 된다.
        if(realEnd < this.endPage){
            this.endPage = realEnd;
        }

        // prev 는 startPage가 1보다 클 경우에만 true
        // next 는 endPage가 realEnd보다 작을 경우에만 true
        this.prev = this.startPage > 1;
        this.next = this.endPage < realEnd;

    }
}
