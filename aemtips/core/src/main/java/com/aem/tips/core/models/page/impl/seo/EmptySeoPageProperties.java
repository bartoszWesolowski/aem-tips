package com.aem.tips.core.models.page.impl.seo;

import com.aem.tips.core.models.page.SeoPageProperties;

public class EmptySeoPageProperties implements SeoPageProperties {

  public static final SeoPageProperties EMPTY = new EmptySeoPageProperties();

  public static SeoPageProperties empty() {
    return EMPTY;
  }

  @Override
  public boolean hideInSearch() {
    return false;
  }

  @Override
  public String hideInSearchMetaTagValue() {
    return "";
  }
}
