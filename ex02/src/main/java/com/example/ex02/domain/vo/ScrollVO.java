package com.example.ex02.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
public class ScrollVO {
    private int ten;
    private int sixty;
    private int hundred;

    public ScrollVO() {
        this(10,40,80);
    }


}
