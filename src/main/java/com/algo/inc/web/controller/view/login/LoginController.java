package com.algo.inc.web.controller.view.login;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/system")
public class LoginController {

    @GetMapping("/login")
    public void login() { log.debug("login"); }

    @GetMapping("/logout")
    public void logout() { log.debug("logout"); }

    @GetMapping("/accessDenied")
    public void accessDenied() { log.debug("accessDenied"); }

    @GetMapping("/adminPage")
    public void adminPage() { log.debug("adminPage"); }
}
