package com.algo.inc.domain.member;


import com.algo.inc.domain.BaseTimeEntity;
import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.reply.Reply;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = {"boardList", "replyList"})
public class Member extends BaseTimeEntity {

    @Id
    private String id;
    private String account;
    private String email;
    private String sex;
    private String password;
    private String phoneNumber;
    private String isAuthEmail;
    private boolean enables;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Board> boardList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Reply> replyList;

}
