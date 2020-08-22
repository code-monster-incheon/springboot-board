package com.algo.inc.domain.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductOrder is a Querydsl query type for ProductOrder
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductOrder extends EntityPathBase<ProductOrder> {

    private static final long serialVersionUID = -732384533L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductOrder productOrder = new QProductOrder("productOrder");

    public final com.algo.inc.domain.QBaseTimeEntity _super = new com.algo.inc.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chgDt = _super.chgDt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.algo.inc.domain.member.QMember member;

    public final ListPath<com.algo.inc.domain.orderProductMap.OrderProductMap, com.algo.inc.domain.orderProductMap.QOrderProductMap> orderProductMapList = this.<com.algo.inc.domain.orderProductMap.OrderProductMap, com.algo.inc.domain.orderProductMap.QOrderProductMap>createList("orderProductMapList", com.algo.inc.domain.orderProductMap.OrderProductMap.class, com.algo.inc.domain.orderProductMap.QOrderProductMap.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    public QProductOrder(String variable) {
        this(ProductOrder.class, forVariable(variable), INITS);
    }

    public QProductOrder(Path<? extends ProductOrder> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductOrder(PathMetadata metadata, PathInits inits) {
        this(ProductOrder.class, metadata, inits);
    }

    public QProductOrder(Class<? extends ProductOrder> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.algo.inc.domain.member.QMember(forProperty("member")) : null;
    }

}

