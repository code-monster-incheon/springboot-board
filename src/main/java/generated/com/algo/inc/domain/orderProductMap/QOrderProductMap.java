package com.algo.inc.domain.orderProductMap;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderProductMap is a Querydsl query type for OrderProductMap
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOrderProductMap extends EntityPathBase<OrderProductMap> {

    private static final long serialVersionUID = -1909121380L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderProductMap orderProductMap = new QOrderProductMap("orderProductMap");

    public final com.algo.inc.domain.QBaseTimeEntity _super = new com.algo.inc.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chgDt = _super.chgDt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.algo.inc.domain.product.QProduct product;

    public final com.algo.inc.domain.order.QProductOrder productOrder;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    public QOrderProductMap(String variable) {
        this(OrderProductMap.class, forVariable(variable), INITS);
    }

    public QOrderProductMap(Path<? extends OrderProductMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderProductMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderProductMap(PathMetadata metadata, PathInits inits) {
        this(OrderProductMap.class, metadata, inits);
    }

    public QOrderProductMap(Class<? extends OrderProductMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.product = inits.isInitialized("product") ? new com.algo.inc.domain.product.QProduct(forProperty("product")) : null;
        this.productOrder = inits.isInitialized("productOrder") ? new com.algo.inc.domain.order.QProductOrder(forProperty("productOrder"), inits.get("productOrder")) : null;
    }

}

