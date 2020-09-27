package com.algo.inc.domain.product;

import lombok.Getter;

@Getter
public enum ProductType {

    SELL("SELL", "판매"),
    ALL("ALL", "전체");

    private String type;
    private String name;

    ProductType(String type, String name)
    {
        this.type = type;
        this.name = name;
    }
}
