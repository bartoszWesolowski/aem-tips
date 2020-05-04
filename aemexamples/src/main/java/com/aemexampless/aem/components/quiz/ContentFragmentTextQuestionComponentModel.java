package com.aemexampless.aem.components.quiz;

import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ContentFragmentTextQuestionComponentModel {

  @Inject
  private ResourceResolver resourceResolver;

  @Inject
  private String contentFragmentPath;

  private TextQuestionContentFragmentModel questionModel;

  @PostConstruct
  protected void init() {
    questionModel = Optional.ofNullable(contentFragmentPath)
      .filter(StringUtils::isNotEmpty)
      .map(path -> StringUtils.join(path, "/jcr:content/data/master"))
      .map(path -> resourceResolver.getResource(path))
      .map(resource -> resource.adaptTo(TextQuestionContentFragmentModel.class))
      .orElse(null);
  }

  public boolean isConfigured() {
    return getQuestionModel()
      .map(TextQuestionContentFragmentModel::isConfigured)
      .orElse(false);
  }

  public String getQuestion() {
    return getQuestionModel()
      .map(TextQuestionContentFragmentModel::getQuestion)
      .orElse(StringUtils.EMPTY);
  }

  public String getAnswer() {
    return getQuestionModel()
      .map(TextQuestionContentFragmentModel::getAnswer)
      .orElse(StringUtils.EMPTY);
  }

  private Optional<TextQuestionContentFragmentModel> getQuestionModel() {
    return Optional.ofNullable(questionModel);
  }
}
