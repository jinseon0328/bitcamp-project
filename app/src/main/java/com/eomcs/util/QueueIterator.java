package com.eomcs.util;

public class QueueIterator extends AbstractIterator{

  // 큐에서 데이터를 꺼내려면 큐 객체를 알아야 한다.
  Queue queue;

  public QueueIterator(Queue queue) throws CloneNotSupportedException {
    // 원본 큐을 손대지 않기 위해(원본 큐의 값을 변경하지 않기 위해) 복제한다.
    // 만약에 클론하다가 예외가 발생하면 JVM에서 알아서 하도록 넘겨버린다.
    this.queue = queue.clone();
  }
  @Override
  public boolean hasNext() {
    return this.queue.size()> 0;
  }
  @Override
  public Object next() {
    return this.queue.poll();
  }

}

