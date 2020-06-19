package com.aem.tips.core.metatags.api;

import java.util.Collections;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public interface MetaTag {

  String getName();

  String getContent();

  default Map<String, String> additionalProperties() {
    return Collections.emptyMap();
  }

  default boolean isValid() {
    return StringUtils.isNoneBlank(getName(), getContent());
  }
}
