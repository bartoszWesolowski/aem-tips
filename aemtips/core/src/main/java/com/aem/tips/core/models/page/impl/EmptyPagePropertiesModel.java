package com.aem.tips.core.models.page.impl;

import com.aem.tips.core.models.page.PagePropertiesModel;
import com.aem.tips.core.models.page.SeoProperties;
import com.aem.tips.core.models.page.impl.seo.EmptySeoProperties;

public class EmptyPagePropertiesModel implements PagePropertiesModel {

  private static final PagePropertiesModel EMPTY = new EmptyPagePropertiesModel();

  public static PagePropertiesModel empty() {
    return EMPTY;
  }

  @Override
  public SeoProperties getSeoProperties() {
    return EmptySeoProperties.empty();
  }
}
