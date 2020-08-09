package com.algo.inc.web.repository;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void boardTest(){
        String name = "김종범";
        User user = new User();
        user.setAccount(name);
        userRepository.save(user);

        for(int i = 0; i< 5; i++){
            Board board = new Board();
            board.setTitle(i +"번째 글");
            board.setUser(user);
            boardRepository.save(board);
        }
        List<Board> boards = boardRepository.findAll();
        for (Board board : boards) {
            System.out.println(board);
        }

        assertThat(boards.get(0).getUser().getAccount()).isEqualTo(name);
        assertThat(boards.size()).isEqualTo(5);
        User user1 = userRepository.findById(1L).get();
        System.out.println(user1);

        List<User> user2 = userRepository.findByAccount("김종범");
    }
}
