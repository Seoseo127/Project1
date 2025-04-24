package com.movie.dao;

import java.sql.*;
import java.util.Scanner;

import com.movie.vo.UserVO;

public class Userid {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        // ID로 회원 조회
        System.out.print("조회할 ID를 입력하세요: ");
        String inputId = scan.nextLine();

        UserVO vo = userDAO.selectById(inputId);

        if (vo != null) {
            // 조회된 회원 정보 출력
            System.out.println("\n=== 회원 정보 조회 결과 ===");
            System.out.println(vo);  // UserVO 클래스에 toString() 메서드 추가로 정보 출력
        } else {
            System.out.println("해당 ID의 회원 정보를 찾을 수 없습니다.");
        }

        scan.close();
    }



}