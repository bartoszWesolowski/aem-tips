package com.aemexampless.aem.components.quiz;

import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TextQuestionContentFragmentModel {

  @Inject
  private String question;

  @Inject
  private String answer;

  public boolean isConfigured() {
    return StringUtils.isNoneBlank(question, answer);
  }

  public String getQuestion() {
    return question;
  }

  public String getAnswer() {
    return answer;
  }
}
