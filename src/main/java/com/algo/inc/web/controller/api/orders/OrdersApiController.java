package com.algo.inc.web.controller.api.orders;

import com.algo.inc.web.dto.orders.OrderRequestParam;
import com.algo.inc.web.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrdersApiController {

    private final OrderService orderService;

    @PostMapping
    public void orderProducts(@RequestBody List<OrderRequestParam> param) throws Exception
    {
        orderService.orderProducts(param);
    }
}
