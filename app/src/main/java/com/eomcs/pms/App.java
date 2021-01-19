package com.eomcs.pms;

// 1) 회원 데이터를 입력하는 코드를 메서드로 분리한다.
// 2) 회원 데이터 목록을 출력하는 코드를 메서드로 분리한다.
// 3) 프로젝트 데이터를 입력하는 코드를 메서드로 분리한다.
// 4) 프로젝트 데이터 목록을 출력하는 코드를 메서드로 분리한다.
// 5) 작업 데이터를 입력하는 코드를 메서드로 분리한다.
// 6) 작업 데이터 목록을 출력하는 코드를 메서드로 분리한다.
// 7) 사용자로부터 입력 받는 코드를 메서드로 분리한다.
// 8) 프롬프트 제목을 파라미터로 받아 출력한다.
// 9) prompt()를 목적에 따라 더 세분화하여 분리한다.
public class App {

  public static void main(String[] args) {

    loop:
      while (true) {
        String command = Prompt.inputString("명령> ");

        switch (command) {
          case "/member/add":
            MemberHandler.add();
            break;
          case "/member/list":
            MemberHandler.list();
            break;
          case "/project/add":
            ProjectHandler.add();
            break;
          case "/project/list":
            ProjectHandler.list();
            break;
          case "/task/add":
            TaskHandler.add();
            break;
          case "/task/list":
            TaskHandler.list();
            break;
          case "quit":
          case "exit":
            System.out.println("안녕!");
            break loop;
          default:
            System.out.println("실행할 수 없는 명령입니다.");
        }
        System.out.println(); // 이전 명령의 실행을 구분하기 위해 빈 줄 출력
      }

    Prompt.close();
  }

}







