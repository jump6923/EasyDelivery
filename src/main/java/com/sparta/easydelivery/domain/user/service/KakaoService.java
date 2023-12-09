package com.sparta.easydelivery.domain.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.easydelivery.domain.user.entity.User;
import com.sparta.easydelivery.domain.user.repository.UserRepository;
import com.sparta.easydelivery.security.jwt.JwtUtil;
import com.sparta.easydelivery.domain.user.dto.KaKaoUserDto;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
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

        KaKaoUserDto kaKaoUserDtoInfo = getKakaoUserInfo(token);

        User kakaoUser = accountIntegrationOrSignupOrLogin(kaKaoUserDtoInfo);

        return jwtUtil.createToken(kakaoUser.getUsername());
    }

    private String getToken(String code) throws JsonProcessingException {
        URI uri = getUri("https://kauth.kakao.com", "/oauth/token");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("redirect_uri", "http://localhost:8080/api/users/kakao/callback");
        body.add("code", code);

        RequestEntity<MultiValueMap<String, String>> request = getRequestEntity(uri, headers, body);

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        return objectMapper.readTree(response.getBody()).get("access_token").asText();
    }

    private KaKaoUserDto getKakaoUserInfo(String token) throws JsonProcessingException {
        URI uri = getUri("https://kapi.kakao.com", "/v2/user/me");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        RequestEntity<MultiValueMap<String, String>> request =
            getRequestEntity(uri, headers, new LinkedMultiValueMap<>());

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        JsonNode jsonNode = objectMapper.readTree(response.getBody());
        long kakaoId = jsonNode.get("id").asLong();
        String email = null;
        if (jsonNode.get("kakao_account").get("email") != null) { // 이메일 동의를 했을 때만 이메일을 받아온다.
            email = jsonNode.get("kakao_account").get("email").asText();
        }

        return new KaKaoUserDto(kakaoId, email);
    }

    private User accountIntegrationOrSignupOrLogin(KaKaoUserDto kaKaoUserDtoInfo) {
        User kakaoUser = userRepository.findByKakaoId(kaKaoUserDtoInfo.getId()).orElse(null);
        if (kakaoUser == null) {
            User sameEmailUser = userRepository.findByEmail(kaKaoUserDtoInfo.getEmail())
                .orElse(null);
            if (sameEmailUser == null) {
                // 기존 이메일중에 카카오 이메일과 같은 게 없으면 새로 회원가입
                String username = UUID.randomUUID().toString();
                String password = passwordEncoder.encode(UUID.randomUUID().toString());
                String email = kaKaoUserDtoInfo.getEmail();
                Long kakaoId = kaKaoUserDtoInfo.getId();
                kakaoUser = User.kakaoSignup(username, password, email, kakaoId);
            } else {
                // 같은 이메일이 있으면 계정 통합
                kakaoUser = sameEmailUser;
                kakaoUser.kakaoIntegration(kaKaoUserDtoInfo.getId());
            }
            userRepository.save(kakaoUser);
        }
        return kakaoUser;
    }

    private static URI getUri(String url, String path) {
        URI uri = UriComponentsBuilder
            .fromUriString(url)
            .path(path)
            .encode()
            .build()
            .toUri();
        return uri;
    }

    private RequestEntity<MultiValueMap<String, String>> getRequestEntity(
        URI uri, HttpHeaders headers, MultiValueMap<String, String> body) {

        RequestEntity<MultiValueMap<String, String>> request = RequestEntity
            .post(uri)
            .headers(headers)
            .body(body);
        return request;
    }

}
