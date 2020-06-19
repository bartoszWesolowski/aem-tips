package com.aem.tips.core.metatags.impl;

import com.aem.tips.core.metatags.api.MetaTag;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SimpleMetaTagImpl that = (SimpleMetaTagImpl) o;
    return Objects.equals(name, that.name) &&
        Objects.equals(content, that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, content);
  }
}
