package com.eomcs.pms;

public class App4 {

  public static void main(String[] args) {
    java.util.Scanner keyboard = new java.util.Scanner(System.in); 
    System.out.print("[회원]");
    String name = keyboard.nextLine("이름?");
    System.out.println("내용");
    System.out.println("이메일: hong@test.com");
    System.out.println("암호: 1111");
    System.out.println("사진: hong.png");
    System.out.println("전화: 1111-2222");
    System.out.printf("%1$tY-%1$tm-%1$td");
    System.out.print("-------------------------------");

    keyboard.close();
  }
}
