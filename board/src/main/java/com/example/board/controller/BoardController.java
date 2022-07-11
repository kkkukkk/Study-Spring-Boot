package com.example.board.controller;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.PageDTO;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/*
Task        URL         Method      Parameter       Form        URL 이동

전체목록    /board/list     GET
등록처리    /board/register POST       모든항목         필요        /board/list
조회       /board/read     GET         bno
삭제처리    /board/remove   GET         bno                      /board/list
수정처리    /board/modify   POST       모든항목         필요        /board/list

*/






@Controller                                     // Spring에게 Controller라고 알려줌
@Slf4j                                          // logging을 하기 위한 어노테이션
@RequestMapping("/board/*")                     // 앞 주소가 /board/로 왔을 때 매핑해줌
@RequiredArgsConstructor                        // 초기화 되지 않은 final 필드나 NonNull이 붙은 필드에 대해 생성자를 생성해줌
public class BoardController {
    private final BoardService boardService;    // 클래스가 아닌 인터페이스를 주입하여 클래스로부터 의존성을 없앤다.

    @GetMapping("/list")                        // /list 라는 url이 get방식으로 왔을때 매핑
//    리턴 타입을 void로 작성해도 되지만
//    다른 컨트롤러에서 getList()를 호출하기 때문에 (remove())
//    html 경로를 직접 문자열로 작성해야 한다.
    public String getList(Criteria criteria, Model model){      // Criteria : 페이징 처리를 위한 pageNum과 게시글 수 amount를 담고있는 클래스
        log.info("***********************");
        log.info("/list");
        log.info("***********************");
        // 화면에 데이터 전달을 위한 모델 겍체에 pageDTO라는 이름의 키에
        // criteria와 게시글 전체 개수를 매개변수로 하는 PageDTO 생성자를 담아준다.
        model.addAttribute("pageDTO", new PageDTO(criteria, boardService.getTotal(criteria)));
        // 모델 객체에 boardList라는 이름의 키에 getList 메소드 실행 결과를 담아준다.
        model.addAttribute("boardList", boardService.getList(criteria));
        // /board/list 로 실행 후 이동할 페이지를 리턴한다.
        return "/board/list";
    }

    // /register라는 주소가 Get방식으로 오면 우선 매핑해 주고
    // 이어서 PostMapping 으로 넘어간다.
    @GetMapping("/register")
    public void register(){}

    // /register라는 주소가 Post방식으로 오면 매핑해준다.
    @PostMapping("/register")
    // 매개변수에 Redirect를 위한 RedirectAttribute 를 담아주고, 리턴을 RedirectView로 해준다.
    public RedirectView register(BoardVO boardVO, RedirectAttributes rttr){
        log.info("***********************");
        log.info("/register");
        log.info("***********************");

        // 매개변수로 받은 boardVO 객체를 담은 register 메소드를 사용한다.
        boardService.register(boardVO);


//        redirect 방식으로 전송할 때에는 request scope를 사용할 수 없다.
//        RedirectAttributes 객체는 두 가지 방법을 제공해준다.
//        1. 쿼리스트링
//           URL 뒤에 전달한 KEY와 VALUE를 쿼리스트링으로 연결해준다.
//        rttr.addAttribute("boardBno",boardVO.getBoardBno());

//        2. Flash 사용
//           (플래시 영역 사용)
//           url에 contextPath를 담고 있음
//           세션에 파라미터를 저장하고, request객체가 초기화 된 후 다시 저장해 준다.

        // register메소드 결과인
        // select된 boardBno를
        // addFlashAttribute를 이용하여 화면으로 전달한다.
        rttr.addFlashAttribute("boardBno",boardVO.getBoardBno());

        // list라는 url을 담고 Redirect시킨다.
        // flash를 사용했을 경우
        // RedirectView는 contextPath를 url에 이미 담고 있다.
        return new RedirectView("list");
    }


    // /read나, /modify 라는 주소가 Get방식으로 왔을 경우 mapping 해준다.
    @GetMapping({"/read", "/modify"})
    // boardBno, criteria, request, model 을 매개변수로 하는 read메소드
    public void read(Long boardBno, Criteria criteria, HttpServletRequest request, Model model){
        log.info("*************");
        // requestURL이라는 문자열에 요청받은 URI를 request.getRequestURI 담아준다.
        // URL을 log로 확인하기 위해
        String requestURL = request.getRequestURI();
        log.info(requestURL.substring(requestURL.lastIndexOf("/")));
        log.info("*************");
        log.info("================================");
        log.info(criteria.toString());
        log.info("================================");
        // 모델 객체에 board라는 키값, read 메소드 실행결과를 value로 담아준다.
        model.addAttribute("board", boardService.read(boardBno));

    }



    //    수정
//    Redirect 방식으로 전송
//    Fash로 데이터 전달 - 수정 성공 시에만 "success" 전달
    @PostMapping("/modify")
    public RedirectView modify(BoardVO boardVO,Criteria criteria, RedirectAttributes rttr){
        log.info("*************");
        log.info("/modify");
        log.info("*************");
        log.info("================================");
        log.info(criteria.toString());
        log.info("================================");
        if(boardService.modify(boardVO)){
//            컨트롤러에서 다른 컨트롤러의 매개변수로 파라미터를 전달할 때에는
//            addAttribute(), 쿼리스트링 방식으로 전달해야 받을 수 있다.
//            Flash방식은 최종 응답 화면에서 사용될 파라미터를 전달할 때에만 사용하도록 한다.
            rttr.addAttribute("boardBno", boardVO.getBoardBno());
            rttr.addAttribute("pageNum", criteria.getPageNum());
            rttr.addAttribute("amount", criteria.getAmount());
        }
        // /board/read url에 RedirectView로 리턴한다.
        return new RedirectView("/board/read");
    }


    @PostMapping("/remove")
    // 인터페이스가 파라미터 자리에 들어가게 되면
    // 구현되어 메소드 파라미터로 들어가게 됨
    public String remove(Long boardBno, Criteria criteria, Model model){
        log.info("*************");
        log.info("/remove");
        log.info("*************");
        boardService.remove(boardBno);
        // 다른 컨트롤러로 이동하고자 할 때 해당 메소드를 직접 실행한다.
        // 만약 필요한 파라미터가 있다면 최초 요청 처리 메소드를 통해
        // 파라미터를 전달해준다.
        // 삭제 이후 list페이지로 보낼 때 넘겨받은 데이터를 갖고 가기 위해
        // getList 메소드를 다시 사용하여 데이터를 가진 채로 list페이지로 보내준다.
        return getList(criteria, model);
    }




}
