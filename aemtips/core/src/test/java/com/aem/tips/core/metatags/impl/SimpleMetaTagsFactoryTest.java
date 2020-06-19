package com.aem.tips.core.metatags.impl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.aem.tips.core.metatags.api.MetaTag;
import org.junit.jupiter.api.Test;

class SimpleMetaTagsFactoryTest {

  @Test
  void shouldReturnDescriptionMetaTag() {
    final MetaTag actual = SimpleMetaTagsFactory.description("content value");
    assertThat(actual).isEqualTo(new SimpleMetaTagImpl("description", "content value"));
  }
}