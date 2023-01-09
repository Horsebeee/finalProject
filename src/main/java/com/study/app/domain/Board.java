package com.study.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@ToString(callSuper = true)
public class Board extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    @ToString.Exclude //순환 참조 issue 막기
    private final Set<BoardComment> boardComments = new LinkedHashSet<>();


    @Setter
    @Column(columnDefinition = "TEXT", nullable = false)
    @Comment("게시글 내용")
    private String content;

    protected Board() {}

    private Board(Long no, User user, String content) {
        this.no = no;
        this.user = user;
        this.content = content;
    }

    public static Board of(Long no, User user, String content) {
       return new Board(no,user,content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return no.equals(board.no);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no);
    }
}
