package com.studio.smp.dev_smp.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginConrtroller {

    @RequestMapping("/login.do")
    public String Login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        return "login/loginView";
    }

}
