package com.eomcs.pms;

import com.eomcs.pms.handler.BoardHandler;
import com.eomcs.pms.handler.MemberHandler;
import com.eomcs.pms.handler.ProjectHandler;
import com.eomcs.pms.handler.TaskHandler;
import com.eomcs.util.Prompt;

public class App {

  public static void main(String[] args) {

    // 각 게시판 데이터를 저장할 메모리 준비
    BoardHandler boardList1 = new BoardHandler();
    BoardHandler boardList2 = new BoardHandler();
    BoardHandler boardList3 = new BoardHandler();
    BoardHandler boardList4 = new BoardHandler();
    BoardHandler boardList5 = new BoardHandler();
    BoardHandler boardList6 = new BoardHandler();
    //------- 설계도를 따라서 저장한 인스턴스 주소 boardList1

    //각 회원 목록 데이터를 저장할 메모리 준비
    //둘다 공유하는 객체
    MemberHandler memberList = new MemberHandler();

    //각 프로젝트 목록 데이터를 저장할 메모리 준비
    //-생성자에서 MemberHandler 객체를 주입하라고 강요한다.
    //-ProjectHandler 객체를 만들려면 반드시 주입해야 한다.
    ProjectHandler projectList = new ProjectHandler(memberList);



    //projectHandler가 의존하는 객체(dependency)를 주입한다.
    // add() 메서드를 호출할 때마다 파라미터에 넘기는 대신에
    // 계속 사용할 수 있도록 인스턴트 필드에 담아 놓는다.
    //프로젝트핸들러에도 주고
    //projectList.memberList = memberList;

    //taskHandler가 의존하는 객체(dependency)를 주입한다.
    //태스크핸들러에도 주고
    //taskList.memberList = memberList;

    //각 작업 목록 데이터를 저장할 메모리 준비
    //TaskHandler taskList = new TaskHandler();
    //각 프로젝트 목록 데이터를 저장할 메모리 준비
    //-생성자에서 MemberHandler 객체를 주입하라고 강요한다.
    //-ProjectHandler 객체를 만들려면 반드시 주입해야 한다.
    TaskHandler taskList = new TaskHandler(memberList);



    loop:
      while (true) {
        String command = com.eomcs.util.Prompt.inputString("명령> ");

        switch (command) {
          case "/member/add":
            memberList.add();
            break;
          case "/member/list":
            memberList.list();
            break;
          case "/project/add":
            projectList.add();
            break;
          case "/project/list":
            projectList.list();
            break;
          case "/task/add":
            taskList.add();
            break;
          case "/task/list":
            taskList.list();
            break;
          case "/board/add":
            boardList1.add();
            break;
          case "/board/list":
            boardList1.list();
            break;
          case "/board2/add":
            boardList2.add();
            break;
          case "/board2/list":
            boardList2.list();
            break;
          case "/board3/add":
            boardList3.add();
            break;
          case "/board3/list":
            boardList3.list();
            break;
          case "/board4/add":
            boardList4.add();
            break;
          case "/board4/list":
            boardList4.list();
            break;
          case "/board5/add":
            boardList5.add();
            break;
          case "/board5/list":
            boardList5.list();
            break;
          case "/board6/add":
            boardList6.add();
            break;
          case "/board6/list":
            boardList6.list();
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
