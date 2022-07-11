package com.example.ex02.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/hw/*")
@Slf4j
public class HomeworkController {

    @GetMapping("/game")
    public String goGame(){
        return "slotmachine/game";
    }

    @PostMapping("/slotmachine/game")
    public String game(String cnt, Model model){
        int money = Integer.parseInt(cnt);
        model.addAttribute("money",money);
        return "slotmachine/money";
    }


    //한 개에서 두 개정도의 데이터를 보낼 때는
    //Model 대신에 @ModelAttribute()를 사용할 수 있다.
    //@ModelAttribute()에 key값, 매개변수가 value값
    //name과 age가 넘어가게 됨
    //@ModelAttribute("KEY") Object obj
    //전달받은 파라미터를 화면 쪽으로 보낼 때 쉽고 간편하게 사용할 수 있다.
    //여러개의 데이터를 보낼 때에는 Model 데이터 전달자를 사용하고,
    //2개 이하의 데이터를 보낼 때에는 @ModelAttribute()를 사용하는 것이 좋다.
    @GetMapping("/info")
    public void getInfo(@ModelAttribute("name") String name, @ModelAttribute("age") Integer age){

    }

    //체크박스와 같은
    //여러 개의 데이터를 받을 때
    //RequestParam()을 사용 (전달받을 파라미터의 이름)
    //ArrayList 등에 넣어주게 됨
    //동일한 이름의 파라미터가 여러개 들어올 때에는 배열 또는 List로 매개변수를 설정한다.
    //이 때 동일한 이름으로 받아야 하기 때문에 @RequestParam("KEY")을 사용해서
    //전달받을 데이터의 KEY값을 지정해준다.
    //KEY 파라미터 명이 전달되면 뒤에 있는 매개변수로 들어간다.
    @GetMapping("/datas")
    public void getDatas(@RequestParam("data") ArrayList<Integer> datas){
        //datas의 크기 log로 찍기
        log.info(String.valueOf(datas.size()));
        //datas를 순서대로 하나 씩 log로 찍기
        datas.stream().map(String::valueOf).forEach(log::info);
    }

    //파라미터 명과 매개변수 명이 다르면 직접 지정해준다.
    @GetMapping("/different")
    public void getData(@RequestParam("data") String name){

    }

}
