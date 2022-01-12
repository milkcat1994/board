package org.zerock.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer") // @ToString 은 항상 exclude
// 연관관계가 있는 변수의 경우 해당 객체의 ToString도 가져와야 하기에 제외 해주는 것 잊지말기
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;
    private String content;

    // 연관관계를 ManyToOne으로 지정
    @ManyToOne(fetch = FetchType.LAZY) //명시적으로 Lazy 로딩 지정
    // 지연 로딩 방식을 추가할 경우 호출지점에서 Transactional 어노테이션을 꼭 붙여야한다.
    private Member writer;
}
