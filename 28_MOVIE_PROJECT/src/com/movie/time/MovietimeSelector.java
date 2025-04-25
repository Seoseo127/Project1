package com.movie.time;

import java.util.List;
import java.util.Scanner;
import java.sql.Timestamp;

public class MovietimeSelector {

    // 상영시간 선택 메소드
    public static void selectScreeningTime(int movieId) {
        Scanner scanner = new Scanner(System.in);

        // 상영시간 조회
        List<String> screeningTime = Movietime.getScreeningTimes(movieId);

        if (screeningTime.isEmpty()) {
            System.out.println("선택할 수 있는 상영시간이 없습니다.");
            return;  // 상영시간이 없다면 더 이상 진행하지 않음
        }

        // 상영시간이 존재하는 경우, 선택지 출력
        System.out.println("상영시간 목록:");
        for (int i = 0; i < screeningTime.size(); i++) {
            System.out.println((i + 1) + ". " + screeningTime.get(i));
        }

        // 사용자가 상영시간 선택
        System.out.print("원하는 상영시간을 선택하세요 (1~" + screeningTime.size() + "): ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > screeningTime.size()) {
            System.out.println("잘못된 선택입니다.");
        } else {
            System.out.println("선택한 상영시간: " + screeningTime.get(choice - 1));
            System.out.println("상영시간이 선택되었습니다.");
        }
    }

    // main 메소드에서 호출 예시
    public static void main(String[] args) {
        // 예시로 영화 ID 1을 입력받음
        int movieId = 1;  // 영화 ID를 직접 지정하거나, 사용자로부터 입력받을 수 있습니다.
        selectScreeningTime(movieId);  // 영화 ID 1의 상영시간을 조회하고 선택
    }
}