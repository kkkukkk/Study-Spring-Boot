package com.example.ex00.dependency;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component //해당 객체를 Spring에서 관리하도록 설정
@Data // getter, setter, toString, equals, hashCode 등을 자동으로 생성해준다
public class Computer {}
