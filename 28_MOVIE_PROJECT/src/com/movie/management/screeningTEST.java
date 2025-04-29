package com.movie.management;

import com.movie.reservation.Movie;
import com.movie.reservation.MovieList;

import java.util.List;
import java.util.Scanner;

public class screeningTEST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ScreeningDAO dao = new ScreeningDAO();
        MovieList movieList = new MovieList();

        while (true) {
            

            System.out.print("===== 상영시간 관리자 메뉴 =======\n상영시간을 관리할 영화 ID를 입력하세요 (0 입력 시 종료):");
            int movieId = Integer.parseInt(sc.nextLine());
            if (movieId == 0) break;

            while (true) {
               System.out.println("\n>> 원하는 작업을 선택하세요:");
                System.out.println("\n1. 상영시간 추가  2. 상영시간 수정  3. 상영시간 삭제  0. 이전 메뉴");
                int menu = Integer.parseInt(sc.nextLine());

                if (menu == 0) break;

                switch (menu) {
                    case 1:
                        System.out.print("추가할 상영 시간 (yyyy-MM-dd HH:mm): ");
                        String insertTime = sc.nextLine();
                        if (dao.insert(movieId, insertTime)) {
                            System.out.println("✅ 상영시간 추가 성공");
                        } else {
                            System.out.println("❌ 상영시간 추가 실패");
                        }
                        break;

                    case 2:
                        System.out.print("기존 상영 시간 (yyyy-MM-dd HH:mm): ");
                        String oldTime = sc.nextLine();
                        System.out.print("변경할 상영 시간 (yyyy-MM-dd HH:mm): ");
                        String newTime = sc.nextLine();
                        if (dao.update(movieId, oldTime, newTime)) {
                            System.out.println("✅ 상영시간 수정 성공");
                        } else {
                            System.out.println("❌ 상영시간 수정 실패");
                        }
                        break;

                    case 3:
                        System.out.print("삭제할 상영 시간 (yyyy-MM-dd HH:mm): ");
                        String deleteTime = sc.nextLine();
                        if (dao.delete(movieId, deleteTime)) {
                            System.out.println("✅ 상영시간 삭제 성공");
                        } else {
                            System.out.println("❌ 상영시간 삭제 실패");
                        }
                        break;

                    default:
                        System.out.println("⚠️ 올바른 번호를 입력하세요.");
                }
            }
        }

        System.out.println("👋 관리자 메뉴를 종료합니다.");
        sc.close();
    }
}





