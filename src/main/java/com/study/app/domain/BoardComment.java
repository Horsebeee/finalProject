package com.study.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Objects;

@Table
@Entity
@Getter
@ToString(callSuper = true)
public class BoardComment extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardCommentNo;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name="user_no")
    private User user;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "board_no")
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
    private Integer orderComment;

    @Comment("댓글그룹: 몇번 게시글의 몇번째 댓글인지 설정")
    private Integer commentGroup;

    protected BoardComment() {}

    private BoardComment( User user, Board board, String content, Integer floor, Integer orderComment, Integer commentGroup) {
        this.user = user;
        this.board = board;
        this.content = content;
        this.floor = floor;
        this.orderComment = orderComment;
        this.commentGroup = commentGroup;
    }

    public static BoardComment of(User user, Board board, String content, Integer floor, Integer orderComment, Integer commentGroup) {
        return new BoardComment(user,board,content,floor,orderComment,commentGroup);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardComment that = (BoardComment) o;
        return boardCommentNo.equals(that.boardCommentNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardCommentNo);
    }
}
