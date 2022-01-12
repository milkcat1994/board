package org.zerock.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer") // @ToString 은 항상 exclude
// 내부적으로 writer_email 과 같이 이름이 바뀌므로 ToString 제외
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;
    private String content;

    // 연관관계를 ManyToOne으로 지정
    @ManyToOne
    private Member writer;
}
