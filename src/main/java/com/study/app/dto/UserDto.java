package com.study.app.dto;

import com.study.app.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Date;

public record UserDto(

        String username,
        String password,
        String nickname,
        String name,
        String phone,
        Date birth,
        Integer gender,
        LocalDateTime reg_date,
        LocalDateTime last_date,
        Integer status

) {
    /* 암호화된 password */


    public static UserDto of(String username, String password, String nickname, String name, String phone, Date birth, Integer gender, LocalDateTime reg_date ,LocalDateTime last_date, Integer status) {
        return new UserDto( username, password, nickname, name, phone, birth, gender, reg_date,last_date,status);
    }

    // 회원정보 수정할 때
    public static UserDto of(String username, String password, String nickname, String name, String phone, Date birth, Integer gender) {
        return new UserDto( username, password, nickname, name, phone, birth, gender, null,null,null);
    }
    // 회원가입 할 때
    public static UserDto of(String username, String password, String nickname, String name) {
        return new UserDto( username, password, nickname, name, null, null, null, null,null,null);
    }



    public static UserDto from(User entity) {
        return new UserDto(
                entity.getUsername(),
                entity.getPassword(),
                entity.getNickname(),
                entity.getName(),
                entity.getPhone(),
                entity.getBirth(),
                entity.getGender(),
                entity.getReg_date(),
                entity.getLast_date(),
                entity.getStatus()
        );
    }

    public User toUpdateEntity() {
        return User.of(
                username,
                password,
                nickname,
                name,
                phone,
                birth,
                gender

        );
    }


}
