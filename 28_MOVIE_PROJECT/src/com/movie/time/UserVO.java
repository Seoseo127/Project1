package com.movie.time;

public class UserVO {
    private String id;   // 사용자 ID
    private String name; // 사용자 이름
    private String email; // 사용자 이메일 (선택 사항)

    // 생성자
    public UserVO(String id, String email) {
        this.id = id;
        this.email = email;
    }

    // Getter와 Setter 메소드
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString() 메소드 (디버깅 용도, 또는 회원 정보를 출력할 때 유용)
    @Override
    public String toString() {
        return "UserVO{id='" + id + "', email='" + email + "'}";
    }
}