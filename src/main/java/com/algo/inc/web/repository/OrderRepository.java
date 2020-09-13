package com.algo.inc.web.repository;

import com.algo.inc.domain.order.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<ProductOrder, Long>{

}
