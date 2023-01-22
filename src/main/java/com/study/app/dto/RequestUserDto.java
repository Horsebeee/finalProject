package com.study.app.dto;

import com.study.app.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public record RequestUserDto(
    @NotBlank(message = "아이디는 필수 입력값입니다.")
    @Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 4~20자리여야 합니다.")

    String username,

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    String password,

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,16}$" , message = "닉네임은 특수문자를 포함하지 않은 4~16자리여야 합니다.")
    String nickname,

    @NotBlank(message = "이름은 필수 입력값입니다.")
    String name


) {
//    public static UserDto of(String username, String password, String nickname, String name) {
//        return new UserDto( username, password, nickname, name, null, null, null, now(),null,1);
//    }


    public User toCreateEntity() {
        return User.of(
                username,
                password,
                nickname,
                name

        );
    }
}
