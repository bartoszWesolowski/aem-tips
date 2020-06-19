package com.aem.tips.core.metatags.api;

import com.google.common.collect.ImmutableList;
import java.util.List;
import org.apache.sling.api.SlingHttpServletRequest;

public interface MetaTagsProvider {

  default MetaTag provide(SlingHttpServletRequest request) {
    return new MetaTag() {
      @Override
      public String getName() {
        return "";
      }

      @Override
      public String getContent() {
        return "";
      }
    };
  }

  default List<MetaTag> provideAll(SlingHttpServletRequest request) {
    return ImmutableList.of(provide(request));
  }

}
