package com.movie.vo;

import java.sql.Date;

public class UserVO {
   private String userId;
   private String userPassword; 
   private String userNickname;
   private String userPhonenumber;
   private String userEmail;
   private Date userBirth;
   
   public UserVO(String userId, String userPassword, String userNickname, String userPhonenumber, String userEmail,
         Date userBirth) {
      super();
      this.userId = userId;
      this.userPassword = userPassword;
      this.userNickname = userNickname;
      this.userPhonenumber = userPhonenumber;
      this.userEmail = userEmail;
      this.userBirth = userBirth;
   }
   
   public String getUserId() {
      return userId;
   }
   public void setUserId(String userId) {
      this.userId = userId;
   }
   public String getUserPassword() {
      return userPassword;
   }
   public void setUserPassword(String userPassword) {
      this.userPassword = userPassword;
   }
   public String getUserNickname() {
      return userNickname;
   }
   public void setUserNickname(String userNickname) {
      this.userNickname = userNickname;
   }
   public String getUserPhonenumber() {
      return userPhonenumber;
   }
   public void setUserPhonenumber(String userPhonenumber) {
      this.userPhonenumber = userPhonenumber;
   }
   public String getUserEmail() {
      return userEmail;
   }
   public void setUserEmail(String userEmail) {
      this.userEmail = userEmail;
   }
   public Date getUserBirth() {
      return userBirth;
   }
   public void setUserBirth(Date userBirth) {
      this.userBirth = userBirth;
   }

   @Override
   public String toString() {
      return "UserVO [userId=" + userId + ", userPassword=" + userPassword + ", userNickname=" + userNickname
            + ", userPhonenumber=" + userPhonenumber + ", userEmail=" + userEmail + ", userBirth=" + userBirth
            + "]";
   }

public String getName() {
	// TODO Auto-generated method stub
	return null;
}
   
   
   
}

