package com.eomcs.pms;

import com.eomcs.pms.handler.BoardHandler;
import com.eomcs.pms.handler.BoardHandler2;
import com.eomcs.pms.handler.BoardHandler3;
import com.eomcs.pms.handler.BoardHandler4;
import com.eomcs.pms.handler.BoardHandler5;
import com.eomcs.pms.handler.MemberHandler;
import com.eomcs.pms.handler.ProjectHandler;
import com.eomcs.pms.handler.TaskHandler;
import com.eomcs.util.Prompt;

// 1) 회원 정보 저장할 때 사용할 메모리의 설계도를 정의한다.
//    - MemberHandler 클래스 안에 Member 클래스를 정의한다.
//    - Member 클래스를 사용하여 회원 정보를 다룬다.
// 2) 프로젝트 정보 저장할 때 사용할 메모리의 설계도를 정의한다.
//    - ProjectHandler 클래스 안에 Project 클래스를 정의한다.
//    - Project 클래스를 사용하여 프로젝트 정보를 다룬다.
// 3) 작업 정보 저장할 때 사용할 메모리의 설계도를 정의한다.
//    - TaskHandler 클래스 안에 Task 클래스를 정의한다.
//    - Task 클래스를 사용하여 작업 정보를 다룬다.
//
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
          case "/board/add":
            BoardHandler.add();
          case "/board/list":
            BoardHandler.list();
          case "/board2/add":
            BoardHandler2.add();
          case "/board2/list":
            BoardHandler2.list();
          case "/board3/add":
            BoardHandler3.add();
          case "/board3/list":
            BoardHandler3.list();

          case "/board4/add":
            BoardHandler4.add();
          case "/board4/list":
            BoardHandler4.list();

          case "/board5/add":
            BoardHandler5.add();
          case "/board5/list":
            BoardHandler5.list();

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
