package org.zerock.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.entity.Reply;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply(){
        IntStream.rangeClosed(1, 300).forEach(i -> {
            // 1~100 의 임의 번호 (랜덤한 Board에 댓글을 남기기 위해서)
            long bno = (long)(Math.random()*100) +1;
            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("Reply......."+ i)
                    .board(board)
                    .replyer("guest")
                    .build();

            replyRepository.save(reply);
        });
    }

    // Member, Board, Reply 까지 자동으로 조인이 되어 쿼리가 실행됨을 알 수 있다.
    // 매번 함께 조인 되는 것은 비효율적이다.
    @Test
    public void testReply1(){
        Optional<Reply> result = replyRepository.findById(1L);
        Reply reply = result.orElse(null);

        System.out.println(reply);
        System.out.println(reply.getBoard());
    }
}
