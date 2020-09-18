package com.algo.inc.web.controller.view.backet;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basket")
@Log
public class BacketViewController {

    @GetMapping("/my-basket")
    public void mybasket()
    {
        log.info("my-basket");
    }

}
