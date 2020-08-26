package com.algo.inc.web.repository;

import com.algo.inc.domain.product.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void 상품_추가하기(){
        String[] str = {"슈즈", "셔츠", "팬츠", "자켓", "캡", "벨트", "니트"};

        for(int i = 0; i < str.length; i++){
            String item = str[i];
            for(int j = 1; j <= 10; j++) {
                Product product = new Product();
                product.setQuantity(30);
                product.setEnabled(true);
                product.setName(item + j);
                productRepository.save(product);
            }
        }
    }
}
