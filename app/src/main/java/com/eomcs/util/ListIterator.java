package com.eomcs.util;

public class ListIterator extends AbstractIterator{

  // 스택에서 데이터를 꺼내려면 스택 객체를 알아야 한다.
  List list;
  int cursor = 0; // 생성자에 커서를 넣자

  public ListIterator(List list) {
    // 리스트는 복제할 필요가 없다.
    // pop()이나 poll()은 꺼내서 없애지만 list는 조회만 하기 때문에 
    // 예외처리나 복제가 필요없다.
    this.list = list;
  }
  @Override
  public boolean hasNext() {
    return cursor < list.size;
  }
  @Override
  public Object next() {
    return this.list.get(cursor++);
    // 위의 문장은 컴파일 하면 다음 문장으로 바뀐다.
    // int temp = cursor;
    // cursor = cursor + 1;
    // return list.get(temp);
  }

}
