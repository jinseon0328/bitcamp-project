package com.eomcs.util;

import java.lang.reflect.Array;

public class List<E> {

  private Node<E> first;
  private Node<E> last;
  protected int size = 0;  

  public void add(E obj) {
    Node<E> node = new Node<>(obj);

    if (last == null) { // 연결 리스트의 첫 항목이라면,
      last = node;
      first = node;
    } else { // 연결리스트에 이미 항목이 있다면, 
      last.next = node; // 현재 마지막 상자의 다음 상자가 새 상자를 가리키게 한다.
      node.prev = last; // 새 상자에서 이전 상자로서 현재 마지막 상자를 가리키게 한다. 
      last = node; // 새 상자가 마지막 상자가 되게 한다.
    }

    size++;
  }

  public Object[] toArray() {
    Object[] arr = new Object[size];

    Node<E> cursor = this.first;
    int i = 0;

    while (cursor != null) {
      arr[i++] = cursor.obj;
      cursor = cursor.next;
    }
    return arr;
  }


  // 제네릭에서 지정한 타입의 배열을 만들어 리턴한다.
  @SuppressWarnings("unchecked") 
  // @SuppressWarnings은 컴파일러가 타입이 맞는지 확인할 수 없는 경우 경고를 띄우는데
  // 그 경고를 띄우지 말라고 지정하고 싶다면 다음 애노테이션을 붙인다.
  public E[] toArray(E[] arr) {

    if (arr.length < size) {
      // 파라미터로 받은 배열이 현재 저장된 항목의 크기보다 작을 경우 
      // 새 배열을 만든다.
      arr = (E[]) Array.newInstance(arr.getClass().getComponentType(), size);
      //                              ------------ 현재 배열의 타입을 알아냄
      //                                           ------------------ 배열의 한 항목의 타입을 알아냄
      //                                ----------------------------- 내가 가진 배열의 타입이다
    } 

    Node<E> cursor = this.first;
    for(int i = 0; i < size; i++) {
      arr[i] = cursor.obj;
      cursor = cursor.next;
    }
    return arr;
  }

  public E get(int index) {
    if(index < 0 || index >= this.size) {
      return null;
    }
    int count = 0;
    Node<E> cursor = first;
    while (cursor != null) {
      if(index == count++) {
        return cursor.obj;
      }
      cursor = cursor.next;
    }
    return null;
  }


  public E delete(int index) {
    if(index < 0 || index >= this.size) {
      return null;
    }
    E deleted = null;
    int count = 0;
    Node<E> cursor = first;
    while (cursor != null) {
      if (index == count++) {
        deleted = cursor.obj; //삭제될 항목을 보관해 둔다.
        this.size--;
        if (first == last) {
          first = last = null;
          break;
        }
        if (cursor == first) {
          first = cursor.next;
          cursor.prev = null;
        } else {
          cursor.prev.next = cursor.next;
          if (cursor.next != null) {
            cursor.next.prev = cursor.prev;
          }
        }
        if (cursor == last) {
          last = cursor.prev;
        }
        break;
      }
      cursor = cursor.next;
    }
    return deleted;
  }

  public boolean delete(E obj) {
    Node<E> cursor = first;
    while (cursor != null) {
      if (cursor.obj.equals(obj)) {//주소가 다르더라도 안에 들어있는 내용물이 같으면 같은 것으로 
        // 간주한다
        this.size--;
        if (first == last) {
          first = last = null;
          return true;
        }
        if (cursor == first) {
          first = cursor.next;
          cursor.prev = null;
        } else {
          cursor.prev.next = cursor.next;
          if (cursor.next != null) {
            cursor.next.prev = cursor.prev;
          }
        }
        if (cursor == last) {
          last = cursor.prev;
        }
        return true;
      }
      cursor = cursor.next;
    }
    return false; //삭제하든 말든 그건 호출이 알아서 하고 일단 나는 리턴해준다?
  }

  public int indexOf(E obj) {

    int index = 0;
    Node<E> cursor = first;

    while (cursor != null) {
      if (cursor.obj == obj) {
        return index;
      }
      cursor = cursor.next;
      index++;
    }
    return -1;
  }

  public int size() {
    return this.size;
  }


  private class Node<T> {
    // 다형적 변수
    // - 해당 클래스의 객체(인스턴스의 주소)뿐만 아니라 
    //그 하위 클래스의 객체(인스턴스의 주소)까지 저장할 수 있다.
    T obj;
    Node<T> next;
    Node<T> prev;

    Node(T obj) {
      this.obj = obj;
    }
  }
  public Iterator<E> iterator() throws CloneNotSupportedException {
    // non-static 중첩 클래스의 인스턴스를 생설할 때는
    // new 연산자 앞에 바깥 클래스의 인스턴스 주소를 줘야 한다.
    return new Iterator<E> () {
      //Iterator iterator = new Iterator() {
      //                      이행하다   Iterator 규칙을
      // non-static 중첩 클래스는 바깥 클래스의 인스턴스가 있어야만
      // 객체를 생성할 수 있다.
      // 객체가 생성될 때, 바깥 클래스의 인스턴스 주소를 내장 필드에 보관한다.
      // 바깥 클래스의 인스턴스 주소를 보관하는 내장 필드를 사용하려면,


      // 스택에서 데이터를 꺼내려면 스택 객체를 알아야 한다.

      int cursor = 0; // 생성자에 커서를 넣자


      @Override
      public boolean hasNext() {
        // 로컬 클래스에서 바깥 클래스에 인스턴스 주소를 사용할 때는
        // => 바깥클래스명. this
        // 예) List.this
        return cursor < List.this.size();
        //             바깥클래스명.this
        //             바깥클래스의 주소를 사용한다
      }
      @Override
      public E next() {
        return List.this.get(cursor++);
        // 위의 문장은 컴파일 하면 다음 문장으로 바뀐다.
        // int temp = cursor;
        // cursor = cursor + 1;
        // return list.get(temp);
      }

    };


    //  return iterator();
  }



}
