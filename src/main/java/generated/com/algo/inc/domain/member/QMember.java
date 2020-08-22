package com.algo.inc.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 256984194L;

    public static final QMember member = new QMember("member1");

    public final com.algo.inc.domain.QBaseTimeEntity _super = new com.algo.inc.domain.QBaseTimeEntity(this);

    public final ListPath<com.algo.inc.domain.basket.Basket, com.algo.inc.domain.basket.QBasket> basketList = this.<com.algo.inc.domain.basket.Basket, com.algo.inc.domain.basket.QBasket>createList("basketList", com.algo.inc.domain.basket.Basket.class, com.algo.inc.domain.basket.QBasket.class, PathInits.DIRECT2);

    public final ListPath<com.algo.inc.domain.board.Board, com.algo.inc.domain.board.QBoard> boardList = this.<com.algo.inc.domain.board.Board, com.algo.inc.domain.board.QBoard>createList("boardList", com.algo.inc.domain.board.Board.class, com.algo.inc.domain.board.QBoard.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> chgDt = _super.chgDt;

    public final StringPath email = createString("email");

    public final BooleanPath enabled = createBoolean("enabled");

    public final StringPath id = createString("id");

    public final StringPath isAuthEmail = createString("isAuthEmail");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final ListPath<com.algo.inc.domain.order.ProductOrder, com.algo.inc.domain.order.QProductOrder> productOrderList = this.<com.algo.inc.domain.order.ProductOrder, com.algo.inc.domain.order.QProductOrder>createList("productOrderList", com.algo.inc.domain.order.ProductOrder.class, com.algo.inc.domain.order.QProductOrder.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDt = _super.regDt;

    public final ListPath<com.algo.inc.domain.reply.Reply, com.algo.inc.domain.reply.QReply> replyList = this.<com.algo.inc.domain.reply.Reply, com.algo.inc.domain.reply.QReply>createList("replyList", com.algo.inc.domain.reply.Reply.class, com.algo.inc.domain.reply.QReply.class, PathInits.DIRECT2);

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final StringPath sex = createString("sex");

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

