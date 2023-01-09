package com.study.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Table(indexes = {
        @Index(columnList = "nickname"),
        @Index(columnList = "id"),
})
@Entity
@Getter
@ToString(callSuper = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Long no;

    @Setter
    @Column(nullable = false, unique = true)
    @Comment("아이디: 로그인 할 때 필요한 정보")
    private String id;

    @Setter
    @Column(nullable = false ,length = 2000)
    @Comment("비밀번호: 암호화 기준으로 length 설정")
    private String pw;

    @Setter
    @Column(nullable = false ,length = 11)
    @Comment("전화번호: - 없이 11자리 기준")
    private char phone;

    @Setter
    @Column(nullable = false)
    @Comment("생년월일")
    private Date birth;

    @Setter
    @Column(nullable = false)
    @Comment("성별: 0 여자, 1 남자")
    private Integer gender;

    @Setter
    @Column(nullable = false, length = 20)
    @Comment("닉네임: 실제 사이트에서 활동하는 이름")
    private String nickname;

    @Setter
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("회원 가입일: 마이페이지에서 보여줄 정보")
    private Date reg_date;

    @Setter
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Comment("마지막 접속일: 마지막 접속날짜를 기준으로 90일 동안 접속하지 않으면 휴면회원으로 전환" +
             "로그인 할 때마다 업데이트 쿼리문 날려주기")
    private Date last_date;

    @Setter
    @Column(nullable = false)
    @ColumnDefault("1")
    @Comment("회원상태: 0 탈퇴한회원, 1 정상회원, 2 휴면회원")
    private Integer status;

    protected User() {}

    private User(String id, String pw, char phone, Date birth, Integer gender, String nickname, Date reg_date, Date last_date, Integer status) {
        this.id = id;
        this.pw = pw;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;
        this.nickname = nickname;
        this.reg_date = reg_date;
        this.last_date = last_date;
        this.status = status;
    }

    public static User of(String id, String pw, char phone, Date birth, Integer gender, String nickname, Date reg_date, Date last_date, Integer status){
        return new User(id,pw,phone,birth,gender,nickname,reg_date,last_date,status);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return no.equals(user.no);
    }

    @Override
    public int hashCode() {
        return Objects.hash(no);
    }
}
