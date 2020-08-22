package com.algo.inc.domain.basket;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBasket is a Querydsl query type for Basket
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBasket extends EntityPathBase<Basket> {

    private static final long serialVersionUID = -2120082686L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBasket basket = new QBasket("basket");

    public final com.algo.inc.domain.QBaseTimeEntity _super = new com.algo.inc.domain.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chgDt = _super.chgDt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.algo.inc.domain.member.QMember member;

    public final com.algo.inc.domain.product.QProduct product;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    public QBasket(String variable) {
        this(Basket.class, forVariable(variable), INITS);
    }

    public QBasket(Path<? extends Basket> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBasket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBasket(PathMetadata metadata, PathInits inits) {
        this(Basket.class, metadata, inits);
    }

    public QBasket(Class<? extends Basket> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.algo.inc.domain.member.QMember(forProperty("member")) : null;
        this.product = inits.isInitialized("product") ? new com.algo.inc.domain.product.QProduct(forProperty("product")) : null;
    }

}

