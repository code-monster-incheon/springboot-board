package com.algo.inc.web.repository;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.reply.Reply;
import com.algo.inc.domain.member.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    @Transactional
    @WithMockUser(username="TestUser", roles={"MEMBER", "ADMIN"})
    public void 특정_게시물에_댓글을_단다() throws Exception
    {
        List<Board> boardPage = boardRepository.findAllBoards();

        Board randomBoard = boardPage.get(0);
        Reply reply = new Reply();

        reply.setBoard(randomBoard);
        reply.setContent("제발 제발 제발 쉬고 싶어");
        reply.setMember(memberRepository.findById("TestUser").get());

        List<Reply> replyList = randomBoard.getReplyList();
        replyList.add(reply);

        boardRepository.save(randomBoard);
    }


}
