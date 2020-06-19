package com.aem.tips.core.metatags.impl;

import com.aem.tips.core.metatags.api.MetaTag;
import com.aem.tips.core.metatags.api.MetaTagsProvider;
import java.util.List;
import org.apache.sling.api.SlingHttpServletRequest;

public class MockedMetaTagProvider implements MetaTagsProvider {

  private List<MetaTag> metaTags;

  public MockedMetaTagProvider(List<MetaTag> metaTags) {
    this.metaTags = metaTags;
  }

  @Override
  public List<MetaTag> provideAll(SlingHttpServletRequest request) {
    return metaTags;
  }
}
