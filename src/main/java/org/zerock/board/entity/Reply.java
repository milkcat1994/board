package org.zerock.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board") // ToString에서 board 제외
// 연관관계가 있는 변수의 경우 해당 객체의 ToString도 가져와야 하기에 제외 해주는 것 잊지말기
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String text;
    private String replyer;

    // Board와의 연관관계 지정
    @ManyToOne
    private Board board;
}
