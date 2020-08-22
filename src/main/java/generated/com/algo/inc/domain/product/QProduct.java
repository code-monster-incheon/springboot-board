package com.algo.inc.domain.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -1726174780L;

    public static final QProduct product = new QProduct("product");

    public final com.algo.inc.domain.QBaseTimeEntity _super = new com.algo.inc.domain.QBaseTimeEntity(this);

    public final ListPath<com.algo.inc.domain.basket.Basket, com.algo.inc.domain.basket.QBasket> basketList = this.<com.algo.inc.domain.basket.Basket, com.algo.inc.domain.basket.QBasket>createList("basketList", com.algo.inc.domain.basket.Basket.class, com.algo.inc.domain.basket.QBasket.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chgDt = _super.chgDt;

    public final BooleanPath enabled = createBoolean("enabled");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<com.algo.inc.domain.orderProductMap.OrderProductMap, com.algo.inc.domain.orderProductMap.QOrderProductMap> orderProductMapList = this.<com.algo.inc.domain.orderProductMap.OrderProductMap, com.algo.inc.domain.orderProductMap.QOrderProductMap>createList("orderProductMapList", com.algo.inc.domain.orderProductMap.OrderProductMap.class, com.algo.inc.domain.orderProductMap.QOrderProductMap.class, PathInits.DIRECT2);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

