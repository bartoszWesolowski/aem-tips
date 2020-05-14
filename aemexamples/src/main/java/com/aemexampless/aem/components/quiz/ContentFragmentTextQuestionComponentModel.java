package com.aemexampless.aem.components.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL, resourceType = "aemexamples/components/quiz/question/contentFragmentTextQuestion")
@Exporter(name = "jackson", extensions = "json", options = { @ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value = "true") })
public class ContentFragmentTextQuestionComponentModel {

  @Inject
  private ResourceResolver resourceResolver;

  @Inject
  private String contentFragmentPath;

  @JsonInclude
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

  @JsonIgnore
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
