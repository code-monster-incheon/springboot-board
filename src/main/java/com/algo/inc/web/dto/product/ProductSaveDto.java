package com.algo.inc.web.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductSaveDto {
    private String name;
    private int price;
    private int quantity;
    private boolean enabled;
}
