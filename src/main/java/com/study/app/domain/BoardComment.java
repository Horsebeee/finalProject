package com.study.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@ToString(callSuper = true)

public class BoardComment extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Setter
    @ManyToOne(optional = false)
    private User user;

    @Setter
    @ManyToOne(optional = false)
    private Board board;

    @Setter
    @Column(nullable = false)
    @Comment("댓글 내용")
    private String content;

    @Column(nullable = false)
    @Comment("계층: 댓글인지 대댓글인지 구분하는 컬럼 0 이면 댓글 1이면 대댓글")
    private Integer floor;

    @Column(nullable = false)
    @Comment("순서: 지정되는 번호값에 따라 정렬되는 순서")
    private Integer order;;

    @Comment("댓글그룹: 몇번 게시글의 몇번째 댓글인지 설정")
    private Integer commentGroup;

    protected BoardComment() {}

    private BoardComment(Long no, User user, Board board, String content, Integer floor, Integer order, Integer commentGroup) {
        this.no = no;
        this.user = user;
        this.board = board;
        this.content = content;
        this.floor = floor;
        this.order = order;
        this.commentGroup = commentGroup;
    }

    public static BoardComment of(Long no, User user, Board board, String content, Integer floor, Integer order, Integer commentGroup) {
        return new BoardComment(no,user,board,content,floor,order,commentGroup);
    }
}
