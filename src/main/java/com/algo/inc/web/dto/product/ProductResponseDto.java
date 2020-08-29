package com.algo.inc.web.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;
    private int quantity;
}
