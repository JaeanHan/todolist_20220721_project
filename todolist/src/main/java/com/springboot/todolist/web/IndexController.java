package com.springboot.todolist.web;

import com.springboot.todolist.config.auth.PrincipalDetails;
import com.springboot.todolist.service.dto.user.UserReqDto;
import com.springboot.todolist.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/test/login") // 이거 oauth 실행안됨
    @ResponseBody
    public String testLogin(Authentication authentication,
                            //해당 어노테이션을 통해 Security Session 에 접근 가능
                            @AuthenticationPrincipal PrincipalDetails principalDetails) { //DI (의존성 주입)
        System.out.println("/test/login ========================");
//        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
//        System.out.println("authentication : " + principalDetails.getUser());

        System.out.println("userDetails : " + principalDetails.getUser());
        return "세션 정보 확인하기";
    }

    @GetMapping("/test/oauth/login")
    @ResponseBody
    public String testOAuthLogin(Authentication authentication,
                                 @AuthenticationPrincipal OAuth2User oauth) { //DI (의존성 주입)
        System.out.println("/test/oauth/login ========================");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authentication : " + oAuth2User.getAttributes());

        System.out.println("oauth2User : " + oauth.getAttributes());

        return "OAuth 세션 정보 확인하기";
    }

    @GetMapping({"", "/"})
    public String index() {
        // 스프링이 권장하고 있는 template engine Mustache (사용은 Thymeleaf)
        // 기본폴더 /src/main/resource
        // 뷰 리졸버 설정 : templates (prefix), .mustache (suffix)
        return "index";
    }
    //OAuth 로그인을 해도 PrincipalDetails
    //일반 로그인을 해도 PrincipalDetails
    @GetMapping("/user")
    @ResponseBody
    public String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("principalDetails : " + principalDetails.getUser());
        return "user";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "manager";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        // 스프링 시큐리티가 해당 주소를 필터로 낚아채버림
        // security config 파일 생성 후 작동 안함
        // 해당 메소드의 url 을 /login -> /loginForm 으로 바꿈
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        // 스프링 시큐리티가 해당 주소를 필터로 낚아채버림
        // security config 파일 생성 후 작동 안함
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(UserReqDto userReqDto) {
        System.out.println(userReqDto);

        String rawPassword = userReqDto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        System.out.println(encPassword.length()); //비밀번호 항상 60자리

        userReqDto.setPassword(encPassword);
        userService.createUser(userReqDto);

        return "redirect:/loginForm";
    }

    @Secured("ROLE_ADMIN") // 특정 메소드에 간단하게 권한 제한을 걸 수 있음. 하나
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "개인정보";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 둘
    @GetMapping("/info2")
    public @ResponseBody String info2() {
        return "개인정보2";
    }

}
