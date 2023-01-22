package com.study.app.controller;

import com.study.app.domain.User;
import com.study.app.dto.RequestUserDto;
import com.study.app.dto.UserDto;
import com.study.app.repository.UserRepository;
import com.study.app.service.UserService;
import com.study.app.validation.CheckNicknameValidator;
import com.study.app.validation.CheckUsernameValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    /* 중복 체크 유효성 검사 */
    private final CheckUsernameValidator checkUsernameValidator;
    private final CheckNicknameValidator checkNicknameValidator;

    /* 커스텀 유효성 검증 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkUsernameValidator);
        binder.addValidators(checkNicknameValidator);
    }

    // 회원가입 페이지로 이동
    @GetMapping("/join")
    public String joinPage(RequestUserDto dto, Model model) {
        model.addAttribute("dto",dto);
        int a =2;
        model.addAttribute("a",a);
System.out.println("회원가입창 들어왔다!!");
        return "join";
    }


    @PostMapping("/join")
    public String join(@Valid RequestUserDto dto, BindingResult bindingResult, Model model) {
System.out.println("들어왔다!!");
        /* 검증 */
        if(bindingResult.hasErrors()) {
            /* 회원가입 실패 시 입력 데이터 값 유지 */
            model.addAttribute("dto", dto);

            /* 유효성 검사를 통과하지 못 한 필드와 메시지 핸들링 */
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put("valid_"+error.getField(), error.getDefaultMessage());
System.out.println("회원가입 실패 ! error message : "+error.getDefaultMessage());
            }

            /* Model에 담아 view resolve */
            for(String key : errorMap.keySet()) {
                model.addAttribute(key, errorMap.get(key));
            }

            /* 회원가입 페이지로 리턴 */
            return "/join";
        }

        // 회원가입 성공 시
       userService.createUser(dto);
System.out.println("회원가입 성공");
        return "redirect:/login";
//            return "회원가입 됐음?";
    }
//    //테스트 회원가입
//    @PostMapping("/testjoin")
//    public String testJoin(RequestUserDto dto,Model model) {
//        System.out.println("테스트창 들어왔니?");
//        System.out.println("테스트창 들어왔니?" + dto);
//        System.out.println("테스트창 들어왔니?" + dto + "정보는 담아왔니?");
//        return "리턴까지 가능하니?";
//
//    }

    // 로그인 페이지로 이동
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }




/** 기본 중복체크 커스터마이징한 유효성 검사 사용 */
//    // 아이디 중복체크
//    @GetMapping("/join/{username}/exists")
//    public ResponseEntity<Boolean> checkUsernameDuplicate(@PathVariable String username){
//        return ResponseEntity.ok(userService.checkUsernameDuplication(username));
//    }
//    // 닉네임 중복체크
//    @GetMapping("/join/{nickname}/exists")
//    public ResponseEntity<Boolean> checkNicknameDuplicate(@PathVariable String nickname){
//        return ResponseEntity.ok(userService.checkUsernameDuplication(nickname));
//    }



    // 비밀번호 찾기 페이지로 이동
    @GetMapping("/findpw")
    public String findpw() {
        return "findpw";
    }
}
