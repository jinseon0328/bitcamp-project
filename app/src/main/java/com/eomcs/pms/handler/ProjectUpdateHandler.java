package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Project;
import com.eomcs.util.Prompt;

public class ProjectUpdateHandler extends AbstractProjectHandler {

  private MemberValidatorHandler memberValidatorHandler;

  public ProjectUpdateHandler(List<Project> projectList, MemberValidatorHandler memberValidatorHandler) {
    super(projectList);
    this.memberValidatorHandler = memberValidatorHandler;
  }

  @Override
  public void service() {
    System.out.println("[프로젝트 변경]");

    int no = Prompt.inputInt("번호? ");

    Project project = findByNo(no);
    if (project == null) {
      System.out.println("해당 번호의 프로젝트가 없습니다.");
      return;
    }

  }
}