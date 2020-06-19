package com.aem.tips.core.metatags.api;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.apache.sling.api.SlingHttpServletRequest;

public interface MetaTagsProvider {

  MetaTag provide(SlingHttpServletRequest request);

  default List<MetaTag> provideAll(SlingHttpServletRequest request) {
    return ImmutableList.of(provide(request));
  }

}
