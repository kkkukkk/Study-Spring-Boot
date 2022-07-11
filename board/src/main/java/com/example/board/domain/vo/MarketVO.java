package com.example.board.domain.vo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
public class MarketVO {
    private long marketNumber;
    private String marketName;
    private String marketOwner;
    private String marketLocation;
    private String marketRegisterDate;


}
