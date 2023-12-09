package com.sparta.easydelivery.domain.user.entity;

public enum UserRoleEnum {
    USER("USER"),  // 사용자 권한
    ADMIN("ADMIN")  // 관리자 권한
    ;

    private String authority;

    UserRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}