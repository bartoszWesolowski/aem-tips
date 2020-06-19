package com.aem.tips.core.metatags.impl;

import com.aem.tips.core.metatags.api.MetaTag;

public class SimpleMetaTagsFactory {

  public static MetaTag description(String content) {
    return new SimpleMetaTagImpl("description", content);
  }

  public static MetaTag robots(String content) {
    return new SimpleMetaTagImpl("robots", content);
  }
}
