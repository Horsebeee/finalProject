package com.study.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import security.BoardPrincipal;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

import static java.text.DateFormat.DEFAULT;
import static java.time.LocalDateTime.now;

@Table(indexes = {
        @Index(columnList = "nickname"),
        @Index(columnList = "username"),
})
@Entity
@Getter
@ToString(callSuper = true)
@DynamicInsert // insert 시 null 인 필드 제외
public class User {


//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_no")
//    private Long userNo;
    @Id
    @Column(nullable = false, unique = true)
    @Comment("아이디: 로그인 할 때 필요한 정보")
    private String username;

    @Setter
    @Column(nullable = false ,length = 2000)
    @Comment("비밀번호: 암호화 기준으로 length 설정")
    private String password;

    @Setter
    @Column(nullable = false, length = 20)
    @Comment("닉네임: 실제 사이트에서 활동하는 이름")
    private String nickname;

    @Setter
    @Column(nullable = false, length = 30)
    @Comment("이름")
    private String name;

    @Setter
    @Column(length = 11)
    @Comment("전화번호: - 없이 11자리 기준")
    private String phone;

    @Setter
    @Column
    @Comment("생년월일")
    private Date birth;

    @Setter
    @Column
    @Comment("성별: 0 여자, 1 남자")
    private Integer gender;



    @Setter
    @Column(nullable = false)
    @Comment("회원 가입일: 마이페이지에서 보여줄 정보")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime reg_date;

    @Setter
    @Comment("마지막 접속일: 마지막 접속날짜를 기준으로 90일 동안 접속하지 않으면 휴면회원으로 전환" +
             "로그인 할 때마다 업데이트 쿼리문 날려주기")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime last_date;

    @Setter
    @Column(nullable = false)
    @ColumnDefault("1")
    @Comment("회원상태: 0 탈퇴한회원, 1 정상회원, 2 휴면회원")
    private Integer status;

//    @Enumerated(EnumType.STRING)
//    private BoardPrincipal.RoleType role; // 역할이 하나인데 굳이 필요한가?

    protected User() {}

    private User(String username, String password,String nickname,String name, String phone, Date birth, Integer gender) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.name =name;
        this.phone = phone;
        this.birth = birth;
        this.gender = gender;

    }

    private User(String username, String password, String nickname, String name,LocalDateTime reg_date) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.reg_date = reg_date;


    }

    public static User of(String username, String password, String nickname, String name, String phone, Date birth, Integer gender){
        return new User(username,password,phone,nickname,name,birth,gender);
    }

    // 회원가입 할 때
    public static User of(String username, String password, String nickname, String name) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return new User(username,password,nickname,name, now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
