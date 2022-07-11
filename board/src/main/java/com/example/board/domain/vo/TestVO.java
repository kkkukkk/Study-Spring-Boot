package com.example.board.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Controller;

@Controller
@Data
public class TestVO {

    private Long testNumber;
    private Long boardBno;
    private String testTitle;
    private String testContent;
    private String testWriter;
    private String testRegisterDate;
    private String testUpdateDate;

}
