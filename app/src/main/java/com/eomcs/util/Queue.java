package com.eomcs.util;

public class Queue<E> extends List<E> implements Cloneable {
  public boolean offer(E e) {
    this.add(e);
    return true;
  }
  public E poll() {
    return this.delete(0);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Queue<E> clone() throws CloneNotSupportedException {
    Queue<E> queue = new Queue<>();
    Object[] values = this.toArray();
    for (Object value : values) {
      queue.offer((E)value);
    }
    return queue;
  }

  @Override
  public Iterator<E> iterator() throws CloneNotSupportedException {
    Queue<E> queue = this.clone();
    return new Iterator<E>() {
      //                      이행하다   Iterator 규칙을
      // 큐에서 데이터를 꺼내려면 큐 객체를 알아야 한다.
      // 만약 로컬 클래스에서 바깥 메서드의 로컬 변수를 사용한다면
      // 컴파일러는 로컬 변수의 값을 저장할 필드를 자동 생성한다.

      @Override
      public boolean hasNext() {
        // 로컬 클래스에서는 바깥 메서드의 로컬 변수를 직접 사용할 수 있다.
        // => 실제적으로는 로컬 클래스에 자동 생성된 필드를 가리킨다.
        return queue.size()> 0;
        //    바깥클래스명.this
      }
      @Override
      public E next() {
        // 로컬 클래스에서는 바깥 메서드의 로컬 변수를 직접 사용할 수 있다.
        return queue.poll();
      }
    };
  }

}
