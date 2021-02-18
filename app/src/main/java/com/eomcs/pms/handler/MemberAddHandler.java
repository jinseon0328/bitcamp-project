package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Member;
import com.eomcs.util.Prompt;

public class MemberAddHandler extends AbstractMemberHandler  {

  // 특정 클래스를 지정하기 보다는
  // List 규칙에 따라 사용할 수 있는 객체를 요구하라!
  // 그러면 훨씬 코드가 유연해진다.

  public MemberAddHandler(List<Member> memberList){
    super(memberList);

  }
  @Override
  public void service() {
    System.out.println("[회원 등록]");

    Member m = new Member();

    m.setNo(Prompt.inputInt("번호? "));
    m.setName(Prompt.inputString("이름? "));
    m.setEmail(Prompt.inputString("이메일? "));
    m.setPassword(Prompt.inputString("암호? "));
    m.setPhoto(Prompt.inputString("사진? "));
    m.setTel(Prompt.inputString("전화? "));
    m.setRegisteredDate(new java.sql.Date(System.currentTimeMillis()));

    memberList.add(m);

    System.out.println("회원을 등록하였습니다.");
  }

}