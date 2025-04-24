package com.movie.dao;

import java.sql.*;
import java.util.Scanner;

import com.movie.vo.UserVO;

public class UserDAO_TEST {

    public static void main(String[] args) {

       Scanner scan = new Scanner(System.in);
      System.out.print("ID : ");
      String userId = scan.nextLine();
      

      System.out.print("PASSWORD : ");
      String userPassword = scan.nextLine();
      

      System.out.print("NICKNAME : ");
      String userNickname = scan.nextLine();
      
      
      System.out.print("PHONE : ");
      String userPhone = scan.nextLine();
      
      System.out.print("EMAIL : ");
      String userEmail = scan.nextLine();
      
      
      System.out.print("BIRTH(YYYY-MM-DD) : ");
      String birth = scan.nextLine();

      Date userBirth = Date.valueOf(birth);
      
      //String userBirth = new Date(2000-1900, 10, 04); //날짜
      
      UserDAO userdao = new UserDAO();
      
      UserVO uservo = new UserVO(userId, userPassword, userNickname, userPhone, userEmail, userBirth);
      userdao.insert(uservo);
      
      
      
      
      
    }
}