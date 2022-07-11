package com.example.ex02.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class WorkVO {
    private String name;
    private String info;
    private String time;
}
