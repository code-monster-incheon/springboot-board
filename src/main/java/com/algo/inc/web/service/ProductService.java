package com.algo.inc.web.service;

import com.algo.inc.domain.product.Product;
import com.algo.inc.web.dto.product.ProductResponseDto;
import com.algo.inc.web.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    // 상품리스트
    public List<ProductResponseDto> getProductList(String command)
    {
        List<Product> productList = new ArrayList<>();
        if(command.equals("ALL"))       // enabled 관계없이
            productList = productRepository.findAll();
        else                            // enabled true인 상품만
            productList = productRepository.findAllByEnabledIsTrue();

        List<ProductResponseDto> list = new ArrayList<>();
        for(int i = 0; i < productList.size(); i++){
            ProductResponseDto productResponseDto = new ProductResponseDto();
            productResponseDto.setId(productList.get(i).getId());
            productResponseDto.setName(productList.get(i).getName());
            productResponseDto.setPrice(productList.get(i).getPrice());
            productResponseDto.setQuantity(productList.get(i).getQuantity());
            list.add(productResponseDto);
        }
        return list;
    }

    // 상품 상세 정보
    public Product getProductDetail(Long id)
    {
        return productRepository.findById(id).get();
    }



}
