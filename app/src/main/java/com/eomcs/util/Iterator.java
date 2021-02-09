package com.eomcs.util;

public interface Iterator {
  // 인터페이스의 메서드는
  // 호출 규칙이기 때문에 무조건 public으로 공개된다.
  // 그래서 public과 abstract는 생략가능.
  //public abstract 
  boolean hasNext() ;
  //public abstract 
  Object next() ;

}
