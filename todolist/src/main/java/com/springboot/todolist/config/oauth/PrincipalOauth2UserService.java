package com.springboot.todolist.config.oauth;

import com.springboot.todolist.config.auth.PrincipalDetails;
import com.springboot.todolist.domain.entity.User;
import com.springboot.todolist.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    @Override
    //구글로 부터 받은 userRequest 데이터에 대한 후처리 되는 함수
    //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration : " + userRequest.getClientRegistration()); // registrationId로 어떤 OAuth로 로그인했는지 확인 가능
        System.out.println("getAccessToken : " + userRequest.getAccessToken().getTokenValue());
//        System.out.println("getClientId : " + userRequest.getClientRegistration().getClientId());
        // 구글 로그인 버튼 클릭 -> 구글로그인 창 -> 로그인을 완료 -> 코드를 리턴(OAuth-Client 라이브러리)
        // -> AccessToken 요청 여기까지가 userRequest 정보
        // userRequest 정보를 통해 -> 회원 프로필 받아야함 (loadUser 함수) -> loadUser 함수가 구글로부터 회원프로필을 받아준다.
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getClientName(); // google
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider + "_" + providerId; // google_123123123 유저네임 충돌 날 이유x
//        String password = bCryptPasswordEncoder.encode("겟인데어"); // 크게 의미 없지만
        String password = "circular reference due to bcrypt";
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        User userEntity = userRepository.findByUsername(username);

        if(userEntity == null ) {
            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .name(name)
                    .email(email)
                    .provider(provider)
                    .providerId(providerId)
                    .build();

            userRepository.save(userEntity);
        }

        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
