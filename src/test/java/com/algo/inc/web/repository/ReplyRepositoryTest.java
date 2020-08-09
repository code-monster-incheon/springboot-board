package com.algo.inc.web.repository;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.reply.Reply;
import com.algo.inc.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReplyRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void replyTest(){
        User user1 = new User();
        Board board = new Board();

        user1.setAccount("김종범");
        userRepository.save(user1);

        board.setUser(user1);
        board.setTitle("QWQQEQWEQWEEQW");
        boardRepository.save(board);
String name ="DDDD";
        User newUser = new User();
        newUser.setAccount(name);
        userRepository.save(newUser);
        Reply reply = new Reply();
        reply.setBoard(board);

        reply.setUser(newUser);
        replyRepository.save(reply);
        List<Reply> r = replyRepository.findAll();

        r.forEach(System.out::println);
        Reply reply1 = replyRepository.findById(1L)
                .orElseThrow(()-> new IllegalArgumentException("없음"));

        assertThat(reply1.getUser().getAccount()).isEqualTo(name);


    }


}
