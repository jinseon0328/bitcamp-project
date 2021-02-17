package com.eomcs.pms.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardHandler {

  private ArrayList<Board> boardList = new ArrayList<>();

  public void add() {
    System.out.println("[게시글 등록]");

    Board b = new Board();

    b.setNo(Prompt.inputInt("번호? "));
    b.setTitle(Prompt.inputString("제목? "));
    b.setContent(Prompt.inputString("내용? "));
    b.setWriter(Prompt.inputString("작성자? "));
    b.setRegisteredDate(new Date(System.currentTimeMillis()));

    boardList.add(b);

    System.out.println("게시글을 등록하였습니다.");
  }


  public void list() throws CloneNotSupportedException {
    System.out.println("[게시글 목록]");

    // 방법 1)
    //Board[] arr = new Board[boardList.size()];
    //BoardList.toArray(arr);
    // 내가 넘겨주는 배열이 넣을 값보다 작다면 배열에 아무것도 안 담긴다

    // 방법 2) - 선생님 추천 방법
    //    Board[] arr = boardList.toArray(new Board[boardList.size()]);
    //    // 내가 넘겨주는 배열이 넣을 값보다 작다면 새로 배열을 만들어서 넘겨줄 거고
    //
    //    for (Board b : arr) {
    //      System.out.printf("%d, %s, %s, %s, %d, %d\n", 
    //          b.getNo(), 
    //          b.getTitle(), 
    //          b.getRegisteredDate(), 
    //          b.getWriter(), 
    //          b.getViewCount(),
    //          b.getLike());
    //    }

    // Iterator 사용하여 데이터 조회하기
    Iterator<Board> iterator = boardList.iterator();

    while (iterator.hasNext()) {
      // 우선 Object에서 받아서 Board로 형변환해야 한다
      Board b = iterator.next();
      // 번호, 제목, 등록일, 작성자, 조회수, 좋아요
      System.out.printf("%d, %s, %s, %s, %d, %d\n", 
          b.getNo(), 
          b.getTitle(), 
          b.getRegisteredDate(), 
          b.getWriter(), 
          b.getViewCount(),
          b.getLike());
    }

  }

  public void detail() {
    System.out.println("[게시글 상세보기]");

    int no = Prompt.inputInt("번호? ");

    Board board = findByNo(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    board.setViewCount(board.getViewCount() + 1);

    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("내용: %s\n", board.getContent());
    System.out.printf("작성자: %s\n", board.getWriter());
    System.out.printf("등록일: %s\n", board.getRegisteredDate());
    System.out.printf("조회수: %d\n", board.getViewCount());

  }

  public void update() {
    System.out.println("[게시글 변경]");

    int no = Prompt.inputInt("번호? ");

    Board board = findByNo(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("제목(%s)? ", board.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s)? ", board.getContent()));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("Y")) {
      board.setTitle(title);
      board.setContent(content);
      System.out.println("게시글을 변경하였습니다.");

    } else {
      System.out.println("게시글 변경을 취소하였습니다.");
    }
  }

  public void delete() {
    System.out.println("[게시글 삭제]");

    int no = Prompt.inputInt("번호? ");

    Board board  = findByNo(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("Y")) {
      boardList.remove(board);  //오버로딩한 메서드를 사용하여 삭제한다.

      System.out.println("게시글을 삭제하였습니다.");

    } else {
      System.out.println("게시글 삭제를 취소하였습니다.");
    }

  }



  private Board findByNo(int boardNo) {
    Board[] list = boardList.toArray(new Board[boardList.size()]);
    //Board[] arr = boardList.toArray(new Board[0]); 게으른 선배 
    //이렇게 하면 가비지가 돼서 
    // 쓰면 안되는 방법이다.
    for (Board b : list) {
      // 처음부터 끝까지 찾을 때는 :를 쓰고 아닐 때는 세미콜론을 쓴다.
      if (b.getNo() == boardNo) {
        return b;
      }
    }
    return null;
  }
}