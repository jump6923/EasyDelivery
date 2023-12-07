package com.sparta.easydelivery.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.easydelivery.security.jwt.JwtUtil;
import com.sparta.easydelivery.user.dto.KaKaoUser;
import com.sparta.easydelivery.user.entity.User;
import com.sparta.easydelivery.user.repository.UserRepository;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    /**
     * 환경 변수로 카카오 디벨로퍼스에서 받은 ClientId 값을 넣으면 됩니다.
     */
    @Value("${kakao.client.id}")
    private String clientId;

    @Value(("${kakao.client.secret}"))
    private String clientSecret;

    public String kakaoLogin(String code) throws JsonProcessingException {
        String token = getToken(code);

        KaKaoUser kaKaoUserInfo = getKakaoUserInfo(token);

        User kakaoUser = accountIntegrationOrSignupOrLogin(kaKaoUserInfo);

        return jwtUtil.createToken(kakaoUser.getUsername());
    }

    private String getToken(String code) throws JsonProcessingException {
        URI uri = UriComponentsBuilder
            .fromUriString("https://kauth.kakao.com")
            .path("/oauth/token")
            .encode()
            .build()
            .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("redirect_uri", "http://localhost:8080/api/users/kakao/callback");
        body.add("code", code);

        RequestEntity<MultiValueMap<String, String>> request = RequestEntity
            .post(uri)
            .headers(headers)
            .body(body);

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        return objectMapper.readTree(response.getBody()).get("access_token").asText();
    }

    private KaKaoUser getKakaoUserInfo(String token) throws JsonProcessingException {
        URI uri = UriComponentsBuilder
            .fromUriString("https://kapi.kakao.com")
            .path("/v2/user/me")
            .encode()
            .build()
            .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RequestEntity<LinkedMultiValueMap<Object, Object>> request = RequestEntity
            .post(uri)
            .headers(headers)
            .body(new LinkedMultiValueMap<>());

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        long kakaoId = jsonNode.get("id").asLong();
        String email = null;
        if (jsonNode.get("kakao_account").get("email") != null) { // 이메일 동의를 했을 때만 이메일을 받아온다.
            email = jsonNode.get("kakao_account").get("email").asText();
        }

        return new KaKaoUser(kakaoId, email);
    }

    private User accountIntegrationOrSignupOrLogin(KaKaoUser kaKaoUserInfo) {
        User kakaoUser = userRepository.findByKakaoId(kaKaoUserInfo.getId()).orElse(null);
        if (kakaoUser == null) {
            User sameEmailUser = userRepository.findByEmail(kaKaoUserInfo.getEmail())
                .orElse(null);
            if (sameEmailUser == null) {
                // 기존 이메일중에 카카오 이메일과 같은 게 없으면 새로 회원가입
                String username = UUID.randomUUID().toString();
                String password = passwordEncoder.encode(UUID.randomUUID().toString());
                String email = kaKaoUserInfo.getEmail();
                Long kakaoId = kaKaoUserInfo.getId();
                kakaoUser = User.kakaoSignup(username, password, email, kakaoId);
            } else {
                // 같은 이메일이 있으면 계정 통합
                kakaoUser = sameEmailUser;
                kakaoUser.kakaoIntegration(kaKaoUserInfo.getId());
            }
            userRepository.save(kakaoUser);
        }
        return kakaoUser;
    }

}
