package com.eomcs.pms.handler;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardHandler {
  //밑의 스태틱은 클래스마다 하나씩만 존재한다.
  static final int LENGTH = 100;
  static Board [] Boards = new Board [LENGTH];  // 레퍼런스 배열 준비  
  static int size = 0;

  public static void add() {
    System.out.println("[게시글 등록]");

    // 1) 회원 정보를 담을 메모리를 준비한다.
    Board b = new Board();

    // 2) 사용자가 입력한 값을 Member 인스턴스에 저장한다.
    b.no = Prompt.inputInt("번호? ");
    b.title = Prompt.inputString("제목? ");
    b.content = Prompt.inputString("내용? ");
    b.writer = Prompt.inputString("작성자? ");
    b.registeredDate = new java.sql.Date(System.currentTimeMillis());

    System.out.println("게시글을 등록하였습니다");

    // 3) 사용자의 정보가 저장된 인스턴스 주소를 레퍼런스 배열에 보관한다.
    Boards [size++] = b;
    // 위 문장은 컴파일할 때 다음 문장으로 변경된다.
    //    int temp = size;
    //    size++;
    //    members[temp] = m;

  }

  public static void list() {
    System.out.println("[게시글 목록]");

    for (int i = 0; i < size; i++) {
      Board b = Boards[i];
      System.out.printf("%d, %s, %s, %s, %s, %d, %d\n", // 출력 형식 지정
          b.no, b.title, b.content, b.writer, 
          b.registeredDate, b.viewcount, b.like);
    }
  }
}