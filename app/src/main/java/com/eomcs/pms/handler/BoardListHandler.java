package com.eomcs.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.eomcs.pms.domain.Board;

public class BoardListHandler extends AbstractBoardHandler  {

  // 특정 클래스를 지정하기 보다는
  // List 규칙에 따라 사용할 수 있는 객체를 요구하라!
  // 그러면 훨씬 코드가 유연해진다.

  public BoardListHandler(List<Board> boardList){
    super(boardList);

  }

  @Override
  public void service() {
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
}