package com.algo.inc.web.controller.api.orders;

import com.algo.inc.web.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrdersAPiController {

    private final OrderService orderService;

}
