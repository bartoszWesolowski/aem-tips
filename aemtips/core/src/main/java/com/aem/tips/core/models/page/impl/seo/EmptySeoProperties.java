package com.aem.tips.core.models.page.impl.seo;

import com.aem.tips.core.models.page.SeoProperties;

public class EmptySeoProperties implements SeoProperties {

  public static final SeoProperties EMPTY = new EmptySeoProperties();

  public static SeoProperties empty() {
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
