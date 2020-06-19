package com.aem.tips.core.metatags.impl;

import com.aem.tips.core.metatags.api.MetaTag;
import com.aem.tips.core.metatags.api.MetaTagsProvider;
import java.util.List;
import org.apache.sling.api.SlingHttpServletRequest;

public class MockedMetaTagProviderThrowingException implements MetaTagsProvider {

  @Override
  public List<MetaTag> provideAll(SlingHttpServletRequest request) {
    throw new RuntimeException("Mocked failure on creating meta tags");
  }
}
