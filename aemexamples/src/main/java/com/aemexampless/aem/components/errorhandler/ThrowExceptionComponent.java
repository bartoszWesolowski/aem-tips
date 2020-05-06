package com.aemexampless.aem.components.errorhandler;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = {SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ThrowExceptionComponent {

  private static final Logger LOGGER = LoggerFactory.getLogger(ThrowExceptionComponent.class);

  @Inject
  private boolean isEditMode;

  @PostConstruct
  public void init() {
    if (!isEditMode) {
      throw new CustomException("Custom exception");
    }
  }
}
