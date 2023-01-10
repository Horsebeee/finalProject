package com.study.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@ToString(callSuper = true)
public class UserLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userLikeNo;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Setter
    @ManyToOne
    @JoinColumn(name = "board_no")
    private Board board;

    protected UserLike() {}

    private UserLike(User user, Board board) {
        this.user = user;
        this.board = board;
    }

    public static UserLike of(User user, Board board) {
       return new UserLike(user,board);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLike like = (UserLike) o;
        return userLikeNo.equals(like.userLikeNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userLikeNo);
    }
}
