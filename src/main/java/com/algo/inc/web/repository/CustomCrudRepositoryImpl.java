package com.algo.inc.web.repository;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.board.QBoard;
import com.algo.inc.domain.member.QMember;
import com.algo.inc.domain.reply.QReply;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;


@Log
public class CustomCrudRepositoryImpl extends QuerydslRepositorySupport implements CustomWebBoard {

    public CustomCrudRepositoryImpl()
    {
        super(Board.class);
    }

    @Override
    public Page<Object[]> getCustomPage(String type, String keyword, org.springframework.data.domain.Pageable pageable) {
        log.info("=================================");
        log.info("Type : " + type);
        log.info("Keyword : " + keyword);
        log.info("Page : " + pageable);
        log.info("=================================");

        QBoard b = QBoard.board;
        QReply r = QReply.reply;
        QMember m = QMember.member;

        JPQLQuery<Board> query = from(b);
        JPQLQuery<Tuple> tuple = query.select(b.id, b.title, r.count(), m.id, b.regDt);
        tuple.innerJoin(m);
        tuple.on(b.member.id.eq(m.id));
        tuple.leftJoin(r);
        tuple.on(b.id.eq(r.board.id));
        tuple.where(b.id.gt(0L));

        if (type != null)
        {
            switch (type.toLowerCase())
            {
                case "t":
                    tuple.where(b.title.like("%" + keyword + "%"));
                    break;
                case "c":
                    tuple.where(b.content.like("%" + keyword + "%"));
                    break;
                case "w":
                    tuple.where(b.member.id.like("%" + keyword + "%"));
                    break;
            }
        }
        tuple.groupBy(b.id);
        tuple.orderBy(b.id.desc());
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> list = tuple.fetch();
        List<Object[]> resultList = list.stream().map(Tuple::toArray).collect(Collectors.toList());

        long total = tuple.fetchCount();

        return new PageImpl<>(resultList, pageable, total);
    }
}
