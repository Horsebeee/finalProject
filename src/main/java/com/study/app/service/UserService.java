package com.study.app.service;

import com.study.app.domain.User;
import com.study.app.dto.RequestUserDto;
import com.study.app.dto.UserDto;
import com.study.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    // 회원 정보 저장하기
    @Transactional
    public void createUser(RequestUserDto dto) {
    //public String createUser(RequestUserDto dto) {
        /* 비밀번호 암호화 */
        System.out.println("암호화전 비밀번호 dto :"+dto.password());

        dto.password().matches(encoder.encode(dto.password()));

        System.out.println("암호화된 비밀번호 dto :"+ encoder.encode(dto.password()));

        RequestUserDto requestUserDto = new RequestUserDto(dto.username(), encoder.encode(dto.password()), dto.nickname(), dto.name());


        User user = requestUserDto.toCreateEntity();
        System.out.println("암호화된 비밀번호 user :"+user.getPassword());
        userRepository.save(user);
        System.out.println("DB에 회원 저장 성공");
        //return user.getUsername();

    }


//    public User saveUser(User user) {
//        validateDuplicateUser(user);
//
//        return userRepository.save(user);
//    }
//
//    private void validateDuplicateUser(User user) {
//        User findUser = userRepository.findByUsername(user.getUsername());
//        if (findUser != null) {
//            throw new IllegalStateException("이미 가입된 회원입니다.");
//        }
//    }

    /* 아이디, 닉네임, 중복 여부 확인 */
    @Transactional(readOnly = true)
    public boolean checkUsernameDuplication(String username) {
        boolean usernameDuplicate = userRepository.existsByUsername(username);
        return usernameDuplicate;
    }

    @Transactional(readOnly = true)
    public boolean checkNicknameDuplication(String nickname) {
        boolean nicknameDuplicate = userRepository.existsByNickname(nickname);
        return nicknameDuplicate;

    }
    /*///////////////////////////*/

}
