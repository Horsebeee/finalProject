package com.study.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@ToString(callSuper = true)
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followNo;



    @Setter
    @ManyToOne
    @JoinColumn(name = "username") // 팔로우 관계를 요청하는 사용자
    @Comment("팔로우 하려고 하는 사람")
    private User user;

    @Setter
    @ManyToOne
    @JoinColumn(name = "following") // 다른 사용자에 의해 팔로우 관계가 생성되는 사용자
    @Comment("팔로우 당하는 사람")
    private User following;

//    @ManyToOne
//    @JoinColumn(name = "username")
//    private User username;

    protected Follow() {}

    private Follow(User username, User following) {
        this.user = username;
        this.following = following;
    }

    public static Follow of(User username, User following) {
        return new Follow(username,following);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Follow follow = (Follow) o;
        return followNo.equals(follow.followNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followNo);
    }
}
