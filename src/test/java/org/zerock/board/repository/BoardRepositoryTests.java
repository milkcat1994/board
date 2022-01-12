package org.zerock.board.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoard(){
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder().email("user"+ i+"@aaa.com").build();

            Board board = Board.builder()
                    .title("Title..."+i)
                    .content("Content...."+i)
                    .writer(member)
                    .build();
            boardRepository.save(board);
        });
    }

    // 지연로딩으로 인한 'no Session' 에러를 해결하기 위해 필요한 어노테이션
    @Transactional
    @Test
    // 자동으로 조인이 되어 쿼리가 실행됨을 알 수 있다.
    public void testRead1(){
        Optional<Board> result = boardRepository.findById(100L);
        Board board = result.orElse(null);

        System.out.println(board);
        System.out.println(board.getWriter());
    }
}
