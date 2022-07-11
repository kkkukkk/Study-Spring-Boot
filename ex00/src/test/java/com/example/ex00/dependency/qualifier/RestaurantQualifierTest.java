package com.example.ex00.dependency.qualifier;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class RestaurantQualifierTest {

    @Autowired
    private Restaurant restaurant;

    @Test
    public void RestaurantQualifierTest(){
        log.info("-----------------");
        log.info("vips steak : " + Restaurant.steak);
        log.info("vips sidebar : " + restaurant.checkSidebar());
        log.info("-----------------");
    }
}
