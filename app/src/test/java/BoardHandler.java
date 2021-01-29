package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardHandler {

  // 공동으로 사용하는 값은 스태틱 필드로 선언한다.


  // 개별적으로 관리해야 하는 값은 인스턴스 필드로 선언한다.
  Node first;
  Node last;
  int size = 0;


  /*
   * 위의 모든 코드는 설계도이다.
   * 밑의 boards는 인스턴스 변수를 뜻하고,
   * 위의 boards는 인스턴스 변수를 넣는 주소를 뜻한다.
   * 
   */

  public void add() {
    System.out.println("[게시글 등록]");

    Board b = new Board();

    b.no = Prompt.inputInt("번호? ");
    b.title = Prompt.inputString("제목? ");
    b.content = Prompt.inputString("내용? ");
    b.writer = Prompt.inputString("작성자? ");
    b.registeredDate = new Date(System.currentTimeMillis());

    /* 위의 한 줄의 코드와 밑의 긴 코드가 같다
     * 
      //배열이 꽉 찼으면, 배열을 늘린다.
      // 1) 새 배열을 만든다. 크기는 기존 배열 크기보다 50% 증가시킨다.
      Board[] arr = new Board[this.boards.length + (this.boards.length / 2)];
      //                                                              >> 1 
      // /2 대신에 쉬프트(오른쪽 1비트 이동)도 가능하다
      // 2) 기존 배열의 값을 새 배열로 복사한다.
      for (int i = 0; i < this.boards.length; i++) {
        arr[i] = this.boards[i];
      }
      // 3) 배열 레퍼런스 boards에 새 배열의 주소를 저장한다. 
      boards = arr;
      System.out.printf("배열 크기 증가(%d)\n", this.boards.length);
     */

    /* 이게 copyOf 메서드인데 이클립스에서 이미 메서드를 만들어 놓았으니
     * 그것을 쓰면 된다
     * Board[] copyOf (Board[] original, int newLength) {
      Board[] arr = new Board[newLength];
      for (int i = 0; i < original.lenth; i++) {
        arr[i] = original[i];
      }
      return arr;
    }
     */
    Node node = new Node(b);

    if (last == null) {
      // 연결 리스트의 첫 번째 상자라면 마지막 상자와 첫 상자가 같은 박스를
      // 가리키게 한다
      last = node;
      first = node;
    } else {
      // 연결 리스트에 이미 다른 박스들이 많다면 마지막 상자 주소를 정해주어야
      // 한다
      last.next = node; //현재 마지막 상자의 다음 상자가 새 상자를 가리키게 한다
      //lastBox로 찾아가서 next라는 변수에 새 박스 주소를 넣는다
      node.prev = last; // 새 상자의 이전 상자 주소를 현재의 마지막 상자로 바꾼다
      last = node; // 새 상자가 마지막 상자가 되게 한다.
      //전의 lastBox 주소를 없애고 새 박스 주소를 넣는다
    }
    
    this.size++;

    System.out.println("게시글을 등록하였습니다.");
  }

  public void list() {
    System.out.println("[게시글 목록]");

    Node cursor = first;

    while (cursor != null) {
      Board b = cursor.board;
      // 커서가 가리키는 주소. 
      // 번호, 제목, 등록일, 작성자, 조회수, 좋아요
      System.out.printf("%d, %s, %s, %s, %d, %d\n", 
          b.no, 
          b.title, 
          b.registeredDate, 
          b.writer, 
          b.viewCount,
          b.like);
      cursor = cursor.next;
      // 커서 변수 = 커서가 들어있는 주소. next에 들어있는 주소값
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

    board.viewCount++;
    System.out.printf("제목: %s\n", board.title);
    System.out.printf("내용: %s\n", board.content);
    System.out.printf("작성자: %s\n", board.writer);
    System.out.printf("등록일: %s\n", board.registeredDate);
    System.out.printf("조회수: %d\n", board.viewCount);

  }

  public void update() {
    System.out.println("[게시글 변경]");

    int no = Prompt.inputInt("번호? ");

    Board board = findByNo(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("제목(%s)? ", board.title));
    String content = Prompt.inputString(String.format("내용(%s)? ", board.content));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("Y")) {
      board.title = title;
      board.content = content;
      System.out.println("게시글을 변경하였습니다.");

    } else {
      System.out.println("게시글 변경을 취소하였습니다.");
    }
  }

  public void delete() {
    System.out.println("[게시글 삭제]");

    int no = Prompt.inputInt("번호? ");

    Board board = findByNo(no);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("Y")) {
      Node cursor = first;
      while (cursor != null) {
        if(cursor.board == board) {
          if(first == last) {
            first = last = null;
            break;
          }
          if (cursor == first) {
            first = cursor.next;
            cursor.prev = null;
          } else {
            cursor.prev.next = cursor.next;
            if(cursor.next != null) {
              cursor.next.prev = cursor.prev;
              
              /* 이렇게 가비지가 된 객체를 없애주는 것이 좋지만,
               * 요즘은 이렇게 하지 않아도 JVM이 알아서 삭제해준다.
              cursor.prev = null; //가비지가 된 객체가 기존 객체를 가리키지 않도록 만든다.
              cursor.next = null; //가비지가 된 객체가 기존 객체를 가리키지 않도록 만든다.
              */
              break;
            }
          }
          if (cursor == last) {
            last = cursor.prev;
          }
          this.size--;
          break;
        }
        cursor = cursor.next;
      }

      System.out.println("게시글을 삭제하였습니다.");

    } else {
      System.out.println("게시글 삭제를 취소하였습니다.");
    }

  }


  // 게시글 번호에 해당하는 인스턴스를 찾아 리턴한다.
  Board findByNo(int boardNo) {
    Node cursor = first;
    while (cursor != null) {
      Board b = cursor.board;
      if (b.no == boardNo) {
        return b;
      }
      cursor = cursor.next;
    }
    return null;
  }

  static class Node {
    Board board;
    Node next;
    Node prev;

    Node(Board b) {
      this.board = b;
    }
  }
}






