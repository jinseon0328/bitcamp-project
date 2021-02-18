package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Project;

public abstract class AbstractProjectHandler implements Command{

  protected List<Project> projectList;
  // protected AbstractMemberHandler memberHandler; 

  public AbstractProjectHandler(List<Project> projectList) {

    this.projectList = projectList;
  }

  protected Project findByNo(int projectNo) {
    Project[] list = projectList.toArray(new Project[projectList.size()]);
    for (Project p : list) {
      // 처음부터 끝까지 찾을 때는 :를 쓰고 아닐 때는 세미콜론을 쓴다.
      if (p.getNo() == projectNo) {
        return p;
      }
    }
    return null;
  }
}