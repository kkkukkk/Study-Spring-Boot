package com.example.ex00.dependency;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor // final 또는 @NonNull이 붙은 필드만 초기화 생성자로 선언
public class Coding {
//  필드 주입
//  굉장히 편하다.
//  final을 붙일 수 없기 때문에 다른 곳에서 변형이 가능하다.
//  순환 참조 시 오류가 발생하지 않기 떄문에 StackOverFlow가 발생한다.
//  @Autowired
    private final Computer computer;

//  생성자 주입 *****
//  순환 참조 시 컴파일러 인지 가능, 오류 발생
//  메모리에 할당하면서 초기값으로 주입되므로 final 키워드 사용 가능,
//  다른 곳에서 변형 불가능하다
//  의존성 주입이 되지 않으면 객체가 생성되지 않으므로
//  NullPointerException(NPE)을 방어할 수 있다.
//    @Autowired
//    public Coding(Computer computer) {
//        this.computer = computer;
//    }
}
