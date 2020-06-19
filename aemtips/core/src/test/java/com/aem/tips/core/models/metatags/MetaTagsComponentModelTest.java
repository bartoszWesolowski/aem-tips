package com.aem.tips.core.models.metatags;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

import com.aem.tips.core.metatags.api.MetaTag;
import com.aem.tips.core.metatags.impl.MetaTagsProvidersRegistry;
import com.google.common.collect.ImmutableList;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class MetaTagsComponentModelTest {

  private static final String PAGE = "/content/page";

  @Mock
  private MetaTagsProvidersRegistry registry;

  @Mock
  private MetaTag metaTag;

  private MetaTagsComponentModel model;

  @BeforeEach
  void setUp(AemContext aemContext) {
    aemContext.create().page(PAGE);
    aemContext.currentPage(PAGE);
    aemContext.registerService(registry);
    when(registry.createMetaTags(aemContext.request())).thenReturn(ImmutableList.of(metaTag));
    model = aemContext.request().adaptTo(MetaTagsComponentModel.class);
  }

  @Test
  void shouldAdaptAndCallServiceWithCorrectProps() {
    assertThat(model).isNotNull();
    assertThat(model.getMetaTags()).containsExactly(metaTag);
  }
}