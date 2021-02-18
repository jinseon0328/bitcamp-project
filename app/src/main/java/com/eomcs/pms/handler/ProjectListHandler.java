package com.eomcs.pms.handler;

import java.util.Iterator;
import java.util.List;
import com.eomcs.pms.domain.Project;

public class ProjectListHandler extends AbstractProjectHandler  {

  // 특정 클래스를 지정하기 보다는
  // List 규칙에 따라 사용할 수 있는 객체를 요구하라!
  // 그러면 훨씬 코드가 유연해진다.

  public ProjectListHandler(List<Project> projectList){
    super(projectList);

  }


  @Override
  public void service() {
    System.out.println("[프로젝트 목록]");

    Iterator<Project> iterator = projectList.iterator();

    while (iterator.hasNext()) {
      Project p = iterator.next();
      System.out.printf("%d, %s, %s, %s, %s, [%s]\n",
          p.getNo(), p.getTitle(), p.getStartDate(), p.getEndDate(), p.getOwner(), p.getMembers());
    }
  }

}