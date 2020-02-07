package com.spboot.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("github")
public class AuthorizeController {

    @GetMapping("login/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state){
        return "index/index";
    }

}
