package com.sparta.easydelivery.domain.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.easydelivery.domain.user.entity.User;
import com.sparta.easydelivery.domain.user.repository.UserRepository;
import com.sparta.easydelivery.security.jwt.JwtUtil;
import com.sparta.easydelivery.domain.user.dto.NaverUserDto;
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
public class NaverService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;

    @Value("${naver.client.id}")
    private String clientId;

    @Value(("${naver.client.secret}"))
    private String clientSecret;


    public String naverLogin(String code) throws JsonProcessingException {
        String token = getToken(code);
        NaverUserDto naverUserDtoInfo = getNaverUserInfo(token);
        User naverUser = accountIntegrationOrSignupOrLogin(naverUserDtoInfo);
        return jwtUtil.createToken(naverUser.getUsername());
    }

    private String getToken(String code) throws JsonProcessingException {
        URI uri = getUri("https://nid.naver.com", "/oauth2.0/token");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("code", code);
        body.add("state", "test");

        RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(uri)
            .body(body);

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        return objectMapper.readTree(response.getBody()).get("access_token").asText();
    }

    private NaverUserDto getNaverUserInfo(String token) throws JsonProcessingException {
        URI uri = getUri("https://openapi.naver.com", "/v1/nid/me");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        RequestEntity<MultiValueMap<String, String>> request = RequestEntity.post(uri)
            .headers(headers).body(new LinkedMultiValueMap<>());

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        String naverId = jsonNode.get("response").get("id").asText();
        String email = null;
        if (jsonNode.get("response").get("email") != null) {
            email = jsonNode.get("response").get("email").asText();
        }
        return new NaverUserDto(naverId, email);
    }

    private User accountIntegrationOrSignupOrLogin(NaverUserDto naverUserDtoInfo) {
        User naverUser = userRepository.findByNaverId(naverUserDtoInfo.getId()).orElse(null);
        if (naverUser == null) {
            User sameEmailuser = userRepository.findByEmail(naverUserDtoInfo.getEmail())
                .orElse(null);
            if (sameEmailuser == null) {
                naverUser = signup(naverUserDtoInfo);
            } else {
                naverUser = sameEmailuser;
                naverUser.naverIntegration(naverUserDtoInfo.getId());
            }
            userRepository.save(naverUser);
        }
        return naverUser;
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

    private User signup(NaverUserDto naverUserDtoInfo) {
        User naverUser;
        String username = UUID.randomUUID().toString();
        String password = passwordEncoder.encode(UUID.randomUUID().toString());
        String email = naverUserDtoInfo.getEmail();
        String naverId = naverUserDtoInfo.getId();
        naverUser = User.naverSignup(username, password, email, naverId);
        return naverUser;
    }
}
