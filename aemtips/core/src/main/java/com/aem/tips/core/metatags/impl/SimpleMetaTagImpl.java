package com.aem.tips.core.metatags.impl;

import com.aem.tips.core.metatags.api.MetaTag;

public class SimpleMetaTagImpl implements MetaTag {

  private String name;

  private String content;

  public SimpleMetaTagImpl(String name, String content) {
    this.name = name;
    this.content = content;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getContent() {
    return content;
  }
}
