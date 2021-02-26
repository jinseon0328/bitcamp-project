package com.eomcs.pms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Project;
import com.eomcs.pms.domain.Task;
import com.eomcs.pms.handler.BoardAddHandler;
import com.eomcs.pms.handler.BoardDeleteHandler;
import com.eomcs.pms.handler.BoardDetailHandler;
import com.eomcs.pms.handler.BoardListHandler;
import com.eomcs.pms.handler.BoardSearchHandler;
import com.eomcs.pms.handler.BoardUpdateHandler;
import com.eomcs.pms.handler.Command;
import com.eomcs.pms.handler.HelloHandler;
import com.eomcs.pms.handler.MemberAddHandler;
import com.eomcs.pms.handler.MemberDeleteHandler;
import com.eomcs.pms.handler.MemberDetailHandler;
import com.eomcs.pms.handler.MemberListHandler;
import com.eomcs.pms.handler.MemberUpdateHandler;
import com.eomcs.pms.handler.MemberValidatorHandler;
import com.eomcs.pms.handler.ProjectAddHandler;
import com.eomcs.pms.handler.ProjectDeleteHandler;
import com.eomcs.pms.handler.ProjectDetailHandler;
import com.eomcs.pms.handler.ProjectListHandler;
import com.eomcs.pms.handler.ProjectUpdateHandler;
import com.eomcs.pms.handler.TaskAddHandler;
import com.eomcs.pms.handler.TaskDeleteHandler;
import com.eomcs.pms.handler.TaskDetailHandler;
import com.eomcs.pms.handler.TaskListHandler;
import com.eomcs.pms.handler.TaskUpdateHandler;
import com.eomcs.util.Prompt;

public class App01 {

  // 사용자가 입력한 명령을 저장할 컬렉션 객체 준비
  static ArrayDeque<String> commandStack = new ArrayDeque<>();
  static LinkedList<String> commandQueue = new LinkedList<>();

  public static void main(String[] args) {

    ArrayList<Board> boardList = new ArrayList<>();
    ArrayList<Member> memberList = new ArrayList<>();
    LinkedList<Project> projectList = new LinkedList<>();
    LinkedList<Task> taskList = new LinkedList<>();

    // 파일에서 데이터를 읽어온다(데이터 로딩)
    try (FileInputStream in = new FileInputStream("boards.data")) {
      int size = in.read() <<8 | in.read();

      // 2) 게시글 개수만큼 게시글을 읽는다.
      for (int i = 0; i < size; i++) {
        //게시글 데이터를 저장할 객체 준비
        Board b= new Board();

        // 게시글 데이터를 읽어서 객체에 저장
        b.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int len = in.read() << 8 | in.read();
        byte[] buf = new byte[len];
        in.read(buf);
        b.setTitle(new String(buf, "UTF-8"));
        // 게시글 객체를 걸렉션에 저장.

        // 게시글 내용 읽어서 저장
        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        b.setContent(new String(buf, "UTF-8"));

        // 게시글 작성자 읽어서 저장
        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        b.setWriter(new String(buf, "UTF-8"));

        // 게시일 읽어서 저장
        len = in.read() << 8 | in.read();
        buf = new byte[len];
        in.read(buf);
        b.setRegisteredDate(Date.valueOf(new String(buf, "UTF-8")));

        // 게시글 조회수 읽어서 저장
        b.setViewCount(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        boardList.add(b);
      }
    }catch (Exception e) {
      System.out.println("게시글 데이터 로딩 중 오류 발생!");
    }


    // 사용자 명령을 처리하는 객체를 맵에 Command    
    HashMap<String, Command> commandMap = new HashMap<>();

    commandMap.put("/board/add", new BoardAddHandler(boardList));
    commandMap.put("/board/list", new BoardListHandler(boardList));
    commandMap.put("/board/delete", new BoardDeleteHandler(boardList));
    commandMap.put("/board/detail", new BoardDetailHandler(boardList));
    commandMap.put("/board/update", new BoardUpdateHandler(boardList));

    commandMap.put("/member/add", new MemberAddHandler(memberList));
    commandMap.put("/member/list", new MemberListHandler(memberList));
    commandMap.put("/member/delete", new MemberDetailHandler(memberList));
    commandMap.put("/member/detail", new MemberUpdateHandler(memberList));
    commandMap.put("/member/update", new MemberDeleteHandler(memberList));
    MemberValidatorHandler memberValidatorHandler = new MemberValidatorHandler(memberList);

    commandMap.put("/project/add", new ProjectAddHandler(projectList, memberValidatorHandler));
    commandMap.put("/project/list", new ProjectListHandler(projectList));
    commandMap.put("/project/delete", new ProjectDetailHandler(projectList));
    commandMap.put("/project/detail", new ProjectUpdateHandler(projectList, memberValidatorHandler));
    commandMap.put("/project/update", new  ProjectDeleteHandler(projectList));


    commandMap.put("/task/add", new TaskAddHandler(taskList, memberValidatorHandler));
    commandMap.put("/task/list", new TaskListHandler(taskList));
    commandMap.put("/task/delete", new TaskDetailHandler(taskList));
    commandMap.put("/task/detail", new TaskUpdateHandler(taskList, memberValidatorHandler));
    commandMap.put("/task/update", new TaskDeleteHandler(taskList));

    commandMap.put("/board/boardserch", new BoardSearchHandler(boardList));

    commandMap.put("/hello", new HelloHandler());


    loop:
      while (true) {

        String command = com.eomcs.util.Prompt.inputString("명령> ");

        if(command.length() == 0) // 사용자가 빈 문자열을 입력하면 다시 입력하도록 요구한다.
          continue;

        //사용자가 입력한 명령을 보관해둔다.
        commandStack.push(command);
        commandQueue.offer(command);

        try {
          // 트라이블럭으로 예외를 장벽을 막았다
          switch (command) {
            case "history": // <== history 명령 추가
              printCommendHistory(commandStack.iterator());
              break;
            case "history2": // <== history2 명령 추가
              printCommendHistory(commandQueue.iterator());;
              break;
            case "quit":
            case "exit":
              System.out.println("안녕!");
              break loop;
            default:
              Command commandHandler = commandMap.get(command);
              if (commandHandler == null) {
                System.out.println("실행할 수 없는 명령입니다.");
              } else {
                commandHandler.service();
                // 이제 명령어와 그 명령어를 처리하는 핸들러를 추가할 때마다
                // case 문을 추가할 필요가 없다.
              }
          }
        } catch (Exception e) {
          System.out.println("------------------------------------------------");
          System.out.printf("명령어 실행 중 오류 발생 :%s - %s\n", 
              e.getClass().getName(), e.getMessage());
          System.out.println("------------------------------------------------");
        }
        System.out.println(); // 이전 명령의 실행을 구분하기 위해 빈 줄 출력
      }

    //게시글 데이터를 파일로 출력한다.
    try (FileOutputStream out = new FileOutputStream("boards.data")) {

      // boards.data 파일 포맷
      // - 2바이트 : 저장된 게시글 개수
      // - n바이트 게시글 데이터
      // - 4바이트 : 게시글 번호
      // - 게시글 제목
      //    -2바이트 : 게시글 제목의 바이트 배열 개수
      //    -x바이트 : 게시글 제목의 바이트 배열
      //  - 게시글 내용
      //    -2바이트 : 게시글 내용의 바이트 배열 개수
      //    -x바이트 : 게시글 내용의 바이트 배열
      //  - 작성자
      //    -2바이트 : 게시글 작성자의 바이트 배열 개수
      //    -x바이트 : 게시글 작성자의 바이트 배열
      //  - 등록일
      //    -2바이트 : 게시글 등록일의 바이트 배열 개수
      //    -x바이트 : 게시글 등록일의 바이트 배열
      //  - 조회수
      //    : 4바이트 게시글 조회수의 바이트 배열
      int size = boardList.size();
      out.write(size >> 8);
      out.write(size);
      for(Board b : boardList) {
        // 게시글 번호
        out.write(b.getNo() >> 24);
        out.write(b.getNo() >> 16);
        out.write(b.getNo() >> 8);
        out.write(b.getNo());

        // 게시글 제목
        byte[] buf = b.getTitle().getBytes("UTF-8");
        // - 게시글 제목의 바이트 개수
        out.write(buf.length >> 8);
        out.write(buf.length);
        // - 게시글 제목의 바이트 배열
        out.write(buf);

        // 게시글 내용
        // - 게시글 내용의 바이트 개수
        buf = b.getContent().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        // - 게시글 내용의 바이트 배열
        out.write(buf);

        // 작성자
        // - 게시글 작성자의 바이트 개수
        buf = b.getWriter().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        // - 게시글 작성자의 바이트 배열
        out.write(buf);

        // 작성일
        // - 작성일의 바이트 개수
        buf = b.getRegisteredDate().toString().getBytes("UTF-8");
        out.write(buf.length >> 8);
        out.write(buf.length);
        // - 작성일 작성자의 바이트 배열
        out.write(buf);

        // 조회수
        out.write(b.getViewCount() >> 24);
        out.write(b.getViewCount() >> 16);
        out.write(b.getViewCount() >> 8);
        out.write(b.getViewCount());
      }
      System.out.println("게시글 데이터 저장!");
    }catch (Exception e) {
      System.out.println("게시글 데이터를 파일로 저장하는 중에 오류 발생하였습니다.");
    }



    Prompt.close();
  }
  static void printCommendHistory(Iterator<String> iterator) {

    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
      if ((++count % 5) == 0) {
        String input = Prompt.inputString(":  ");
        if (input.contentEquals("q")) {
          break;
        }
      }
    }
  }
}
