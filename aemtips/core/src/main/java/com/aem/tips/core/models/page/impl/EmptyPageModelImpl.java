package com.aem.tips.core.models.page.impl;

import com.aem.tips.core.models.page.PageModel;
import com.aem.tips.core.models.page.SeoPageProperties;
import com.aem.tips.core.models.page.impl.seo.EmptySeoPageProperties;

public class EmptyPageModelImpl implements PageModel {

  private static final PageModel EMPTY = new EmptyPageModelImpl();

  public static PageModel empty() {
    return EMPTY;
  }

  @Override
  public SeoPageProperties getSeoProperties() {
    return EmptySeoPageProperties.empty();
  }
}
