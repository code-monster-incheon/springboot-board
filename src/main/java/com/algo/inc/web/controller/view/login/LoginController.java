package com.algo.inc.web.controller.view.login;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log
@RequestMapping("/system")
public class LoginController {

    @GetMapping("/login")
    public void login() { log.info("login"); }

    @GetMapping("/logout")
    public void logout() { log.info("logout"); }

    @GetMapping("/accessDenied")
    public void accessDenied() { log.info("accessDenied"); }

    @GetMapping("/adminPage")
    public void adminPage() { log.info("adminPage"); }
}
