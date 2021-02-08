package com.eomcs.util;

public abstract class AbstractIterator {
  // 구체적인 구현은 서브 클래스에게 맡긴다.
  // 헐... 이렇게 아무것도 안하는 슈퍼 클래스를 정의할 필요가 있을까?
  // - 서브 클래스를 구현하는 개발자에게 규칙에 따라 만들도록 강제하는 효과가 있다.
  // 즉 개발자가 서브 클래스를 정의할 때 규칙에 슈퍼 클래스를 따라 만들도록 강제하는
  // 효과가 있다.
  // 이터레이터를 같은 타입으로 묶는 효과가 있다.

  public abstract boolean hasNext() ;
  public abstract Object next() ;

}

