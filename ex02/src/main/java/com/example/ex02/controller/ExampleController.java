package com.example.ex02.controller;

import com.example.ex02.domain.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/ex/*")
@Slf4j
public class ExampleController {

    // value 에 경로를 작성하고, method 에 호출할 서블릿 메소드를 설정한다.
    @RequestMapping(value = "/example", method = {RequestMethod.GET, RequestMethod.POST})
    public void ex01(){
        log.info("ex01----------------------");
    }

    @GetMapping("")
    public void ex02(){
        log.info("ex02--------------");
    }

    @GetMapping("/ex03")
    public String ex03(ExampleVO exampleVO){
        log.info("--------------------------------");
        log.info(exampleVO.toString());
        log.info("--------------------------------");
        return "ex03";
    }

    @GetMapping("/ex04")
    public String ex04(TaskVO taskVO){
        log.info("--------------------------------");
        taskVO.toString();
        log.info("--------------------------------");
        return "ex04";
    }

//  경로 : localhost:10002/ex/login
//  acton : ex/login -> 결과 : /ex/ex/login
//  action : /ex/login -> 결과 : /ex/login

    @GetMapping("/login")
    public void login(){}

    @PostMapping("/login")
    public String login(LoginVO loginVO) {

        return loginVO.getId().equals("admin") ? "/admin" : "/user";
    }


    
//  실습
//  domain.vo.TaskVO 선언
//  int num, int kor, int eng, int math 선언
//  "ex/ex04" URL 요청에 실행될 메소드 선언
//  GET 방식
//  ex04.html 선언
//  총점과 평균 출력

    //아이디와 비밀번호를 입력받은 후
    //아이디가 admin일 경우 admin.html로 이동
    //아이디가 user일 경우 user.html로 이동
    //메소드는 리턴타입을 void로 선언한다.

    //- admin.html : 관리자 페이지 출력
    //- user.html : 일반 회원 페이지 출력

    // 이름을 입력하고 출근 또는 퇴근 버튼을 클릭한다.
    // 출근 시간은 09:00이며, 퇴근 시간 18:00이다.
    // 출근 버튼 클릭 시 9시가 넘으면 지각으로 처리하고,
    // 퇴근 버튼 클릭 시 18시 전이라면 퇴근이 아닌 업무시간
//    문자열을 Date 타입으로 변경 시키는 방법
//    - SimpleDateFormat 생성자에 전달받은 날짜 형식을 작성한다.
//    - parse() 메소드에 작성한 형식에 맞는 문자열을 전달하면 Date타입으로 변한다.
//    - format() 메소드에 Date 타입을 전달하면 문자열 형식으로 변한다.


    @GetMapping("/work/checkBtn")
    public void checkBtn(){
    }
    @PostMapping("/work/checkBtn")
    public String checkBtn(WorkVO workVO){

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); // 현재 시간에서 시간에 해당하는 값
        int min = Calendar.getInstance().get(Calendar.MINUTE);        // 현재 시간에서 분에 해당하는 값
        String info = workVO.getInfo(); // 출근 퇴근 버튼에 부여한 value
        String ap = ""; //am pm 표시
        String fmin = String.format("%02d",min); // minute이 0~9 일 때 자리수 채워넣기

        int time = Integer.parseInt(""+hour+fmin); // 문자열로 붙힌 뒤 정수로 바꾸어 사용
        if (hour < 12){                             // 시간이 12보다 작으면 AM 아니면 PM
            ap = "AM";
        } else{
            ap = "PM";
        }
        workVO.setTime(ap + " " + hour + "시 " + min + "분"); // 페이지에 현재 시간 띄워주기 위해

        if (info.equals("come")){   // 출근 버튼에 부여한 value
            if(time < 900) {return "ex/work/getToWork";}   // 시간+분을 붙힌 값이 900보다 작으면 출근
            else {return "ex/work/late";}                  // 아니면 지각
        }else if(info.equals("leave")) {    // 퇴근 버튼에 부여한 value
            if (time > 1800) {return "ex/work/leaveWork";} // 퇴근버튼을 누르고 1800보다 크다면 퇴근
            else {return "ex/work/work";}                  // 아니면 일하기
        }

//        html의 input:time으로 입력받은 값으로 구해보기

//        String info = workVO.getInfo();
//        String time = workVO.getTime();
//        String ap = "";
//
//        String[] tms = time.split(":"); // input:time이 19:00 형식으로 넘어오므로 split해서 저장
//        if (Integer.parseInt(tms[0]) < 12){   // tms의 0번째 인덱스에 시간이 담기게 됨, 시간으로 비교
//            ap = "AM";
//        } else{
//            ap = "PM";
//        }
//        int tm = Integer.parseInt(String.join("",tms));   // 시간 + 분을 붙혀준 뒤 정수로 형 변환
//        workVO.setTime(ap + " " + tms[0] + "시 " + tms[1] + "분"); // 페이지에 띄워주기 위해
//
//        if (info.equals("come")){                         //위와 동일한 조건문
//            if(tm < 900) {return "ex/work/getToWork";}
//            else {return "ex/work/late";}
//        }else if(info.equals("leave")) {
//            if (tm > 1800) {return "ex/work/leaveWork";}
//            else {return "ex/work/work";}
//        }

        return null;
    }

//    실습

//    무기를 강화하기 위해서 아래에 있는 강화 주문서를 사용할 수 있다.
//    10% 공격력 주문서 : 공격력 + 80
//    60% 공격력 주문서 : 공격력 + 40
//    100% 공격력 주문서 : 공격력 + 10

//    한 번만 강화할 수 있으며, 10% 확률로 대성공을 한다.
//    대성공 시 해당 주문서 공격력의 5배가 증가한다.

//    강화하기 버튼을 눌렀을 때 알맞는 결과를 출력한다.

//    ScrollVO 클래스 선언
//    10%, 60%, 100% 주문서의 공격력 수치를 저장한다.
//    기본 생성자를 호출 했을 때에는
//    위에 작성된 공격력 수치를 기본 값으로 설정하고
//    만약 새로운 값을 받게 되면 해당 공격력 수치로
//    변경되도록 생성자를 오버로딩한다.
//    int 배열 선언 int[] a = new int[10];
//    Random rd = new Random();
//    int idx = rd.nextInt(10);
//    if(ar[idx] == 1){
//      idx = rd.nextInt(10);
//      if(ar[idx] == 1){
//
//      }// 대성공
//    }



    @GetMapping("/upgrade")
    public String upgrade(){
        return "upgrade/form";
    }

    @PostMapping("/upgrade")
    public String upgrade(String choice, Model model){
        log.info("0000000000000000000000");
        log.info(":::::choice::::::" + choice);
        log.info("0000000000000000000000");

        ScrollVO scrollVO = new ScrollVO();
        int strength = 0;
        boolean check = false;

        switch(Integer.parseInt(choice)){
            case 10:
                check = getChance(10);
                strength = scrollVO.getTen();
                break;
            case 60:
                check = getChance(60);
                strength = scrollVO.getSixty();
                break;
            case 100:
                check = getChance(100);
                strength = scrollVO.getHundred();
                break;
        }
        if(!check){return "upgrade/fail";}
        if(getChance(100)){
            strength *= 5;
            model.addAttribute("strength", strength);
            return "upgrade/bigSuccess";
        }
        model.addAttribute("strength", strength);
        return "upgrade/success";
    }

    public boolean getChance(int rate){
        Random rd = new Random();
        int[] arr = new int[10];
        int index = rd.nextInt(arr.length);
        for (int i = 0; i < rate/10; i++) {
            arr[i] = 1;
        }
        return arr[index] == 1;
    }

//    실습
//    사용자가 입력한 바코드 번호에 알맞는 상품 명을 전달한다.

    @GetMapping("market")
    public String market(){return "product/market";}

    @PostMapping("check")
    public String check(String barcode, Model model){
        String productName="";
        switch(barcode){
            case "4383927":
                productName="오징어 땅콩";
                break;
            case "0832147":
                productName="초코 우유";
                break;
            case "9841631":
                productName="벌꿀 피자";
                break;
            case "5587578":
                productName="샌드위치";
                break;
        }
        model.addAttribute("productName",productName);
        log.info(barcode);
        return "product/cashier";
    }

    // 아이디 : apple
    // 비밀번호 : banana
    // 로그인 성공 시 apple님 환영합니다.
    // 로그인 실패 시 로그인 실패

    @GetMapping("join")
    public String join(){return "join/join";}

    @PostMapping("/join")
    public String join(JoinVO joinVO, Model model){
        if(joinVO.getUserId().equals("apple") && joinVO.getUserPw().equals("banana")){
            model.addAttribute("userId",joinVO.getUserId());
            return "join/good";
        }else{
            return "join/bad";
        }
    }

    // 노래방 기계 제작
    // 사용자의 점수에 따른 알맞는 메세지 출력

    @GetMapping("/sing")
    public String sing(){
        return "sing/singPoint";
    }

    @PostMapping("/sing")
    public String sing(String singPoint, Model model){

        int sp = Integer.parseInt(singPoint);
        String message="";

        if(sp > 90){
            message = "오우 가수이신가요?";
        }else if(sp > 80){
            message = "잘 부르시네요?";
        }else if(sp > 70){
            message = "아쉽습니다?";
        }else if(sp > 60){
            message = "에잉 별로...";
        }else {
            message = "나가주세요우...";
        }

        model.addAttribute("message", message);
        return "sing/singMessage";
    }

}
