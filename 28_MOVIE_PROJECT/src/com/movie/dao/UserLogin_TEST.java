package com.movie.dao;
import com.movie.dao.UserDAO;
import com.movie.vo.UserVO;
import com.movie.dao.userLogin;

import java.util.Scanner;

public class UserLogin_TEST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserDAO dao = new UserDAO();  // 여기서 컴파일 에러 가능

        System.out.print("아이디를 입력하세요: ");
        String userId = sc.nextLine();

        System.out.print("비밀번호를 입력하세요: ");
        String userPw = sc.nextLine();

        UserVO loginUser = dao.login(userId, userPw);  // 여기서도 가능

        if (loginUser != null) {
            System.out.println("✅ 로그인 성공!");
            System.out.println("환영합니다, " + loginUser.getUserNickname() + "님!");
        } else {
            System.out.println("❌ 로그인 실패: 아이디 또는 비밀번호가 틀렸습니다.");
        }

        sc.close();
    }
}

