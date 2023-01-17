package com.study.app.dto;

import com.study.app.domain.User;

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
    Date reg_date,
    Date last_date,
    Integer status

) {
    public static UserDto of(String username, String password, String nickname, String name, String phone, Date birth, Integer gender, Date reg_dateDate ,Date last_date, Integer status) {
        return new UserDto( username, password, nickname, name, phone, birth, gender, reg_dateDate,last_date,status);
    }

    // 회원정보 수정할 때
    public static UserDto of(String username, String password, String nickname, String name, String phone, Date birth, Integer gender) {
        return new UserDto( username, password, nickname, name, phone, birth, gender, null,null,null);
    }
    // 회원가입 할 때
    public static UserDto of(String username, String password, String nickname, String name,String phone) {
        return new UserDto( username, password, nickname, name, phone, null, null, null,null,null);
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

    public UserDto toUpdateEntity() {
        return UserDto.of(
                username,
                password,
                nickname,
                name,
                phone,
                birth,
                gender
        );
    }

    public UserDto toCreateEntity() {
        return UserDto.of(
                username,
                password,
                nickname,
                name,
                phone
        );
    }
}
