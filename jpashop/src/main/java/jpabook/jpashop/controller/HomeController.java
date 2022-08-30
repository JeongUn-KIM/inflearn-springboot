package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j   //logger 코드 쉽게 사용가능
public class HomeController {

    @RequestMapping("/")
    public String home() {
        log.info("home controller");  //log에 home controller 찍힘
        return "home";
    }
}
