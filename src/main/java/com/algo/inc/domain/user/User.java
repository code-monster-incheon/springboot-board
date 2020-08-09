package com.algo.inc.domain.user;


import com.algo.inc.domain.BaseTimeEntity;
import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.reply.Reply;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = {"boardList", "replyList"})
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    private String email;
    private String sex;
    private String password;
    private String phoneNumber;
    private String isAuthEmail;
    private boolean enables;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Board> boardList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Reply> replyList;

}
