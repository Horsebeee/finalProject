package com.study.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
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
    @JoinColumn(name = "followerId") // 나를 구독한 사람
    @Comment("팔로워: 나를 구독한 사람")
    private User followerId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "followingId") // 내가 구독한 사람
    @Comment("팔로잉: 내가 구독한 사람")
    private User followingId;

    protected Follow() {}

    private Follow(User followerId, User followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public static Follow of(User followerId, User followingId) {
        return new Follow(followerId,followingId);
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
