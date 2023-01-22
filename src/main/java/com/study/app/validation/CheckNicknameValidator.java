package com.study.app.validation;



import com.study.app.dto.RequestUserDto;
import com.study.app.dto.UserDto;
import com.study.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckNicknameValidator extends AbstractValidator<RequestUserDto>{
    private final UserRepository userRepository;

    @Override
    protected void doValidate(RequestUserDto dto, Errors errors) {
        if (userRepository.existsByUsername(dto.toCreateEntity().getNickname())) {
            /* 중복인 경우 */
            errors.rejectValue("nickname","닉네임 중복 오류", "이미 사용 중인 닉네임입니다.");
        }
    }
}
