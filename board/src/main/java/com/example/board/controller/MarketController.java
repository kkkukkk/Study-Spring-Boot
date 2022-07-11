package com.example.board.controller;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.MarketVO;
import com.example.board.service.MarketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/market/*")
@RequiredArgsConstructor
public class MarketController {
    public final MarketService marketService;

    @PostMapping(value="/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public String insert(@RequestBody MarketVO marketVO){
        marketService.register(marketVO);
        return "success";
    }

    @DeleteMapping("/{mno}")
    public String remove(@PathVariable("mno") Long marketNumber){
        return marketService.remove(marketNumber) ? "삭제 성공" : "삭제 실패";
    }

    @GetMapping("/list/{mno}")
    public MarketVO read(@PathVariable("mno") Long marketNumber){
        return marketService.read(marketNumber);
    }

    @PostMapping(value="/list/{page}", consumes = "application/json")
    public List<MarketVO> getList(@PathVariable("page") int pageNum){
        return marketService.getList(new Criteria(pageNum, 10));
    }

    @PatchMapping(value="/{mno}", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public String modify(@PathVariable("mno") Long marketNumber){
        MarketVO marketVO = new MarketVO();
        marketVO.setMarketNumber(marketNumber);
        marketVO.setMarketName("장사네가게");
        marketVO.setMarketOwner("김장사");
        marketVO.setMarketLocation("우리집근처");

        return marketService.modify(marketVO) ? "수정 성공" : "수정 실패";
    }

}
