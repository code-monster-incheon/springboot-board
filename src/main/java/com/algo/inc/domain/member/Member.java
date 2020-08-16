package com.algo.inc.domain.member;


import com.algo.inc.domain.BaseTimeEntity;
import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.reply.Reply;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString(exclude = {"boardList", "replyList"})
@Accessors(chain = true)
public class Member extends BaseTimeEntity {

    @Id
    private String id;
    private String name;
    private String email;
    private String sex;
    private String password;
    private String phoneNumber;
    private String isAuthEmail;
    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    private List<Board> boardList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
    private List<Reply> replyList;

    @Builder
    public Member(String id, String name, String password, Role role)
    {
        this.id = id;
        this.password = password;
        this.name = name;
        this.role = role;
    }
}
