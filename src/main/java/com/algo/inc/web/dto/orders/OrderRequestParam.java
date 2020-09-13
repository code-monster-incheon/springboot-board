package com.algo.inc.web.dto.orders;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestParam {
    private Long prodId;
    private int cnt;

    public OrderRequestParam(Long prodId, int cnt)
    {
        this.prodId = prodId;
        this.cnt = cnt;
    }
}
