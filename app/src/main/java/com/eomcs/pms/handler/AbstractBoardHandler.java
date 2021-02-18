package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Board;
// 이 클래스를 상속 받는 서브 클래스는
// 반드시 Command 규칙을 따르도록 강제한다
public abstract class AbstractBoardHandler implements Command {

  protected List<Board> boardList;

  public AbstractBoardHandler(List<Board> boardList) {
    this.boardList = boardList;
  }

  protected Board findByNo(int boardNo) {
    // 서브 클래스까지만 공개
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