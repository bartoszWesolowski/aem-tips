package com.aem.tips.core.metatags.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.aem.tips.core.metatags.api.MetaTag;

public class MockedMetaTag {

  public static MetaTag invalid() {
    return create(false);
  }

  public static MetaTag valid() {
    return create(true);
  }

  private static MetaTag create(boolean isValid) {
    final MetaTag metaTag = mock(MetaTag.class);
    when(metaTag.isValid()).thenReturn(isValid);
    return metaTag;
  }
}
