package com.example.ex03.controller.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Controller;

@Controller
@Data
public class FileVO {
    private String fileName;
    private String uploadPath;
    private String uuid;
    private boolean image;
}
