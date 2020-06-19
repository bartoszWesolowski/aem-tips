package com.aem.tips.core.metatags.impl.providers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.aem.tips.core.metatags.api.MetaTag;
import com.aem.tips.core.metatags.impl.SimpleMetaTagsFactory;
import com.day.cq.wcm.api.PageManager;
import com.google.common.collect.ImmutableMap;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AemContextExtension.class)
class DescriptionMetaTagProviderTest {

  private static final String WITH_DESCRIPTION = "/content/page-with-description";
  private static final String WITHOUT_DESCRIPTION = "/content/page-without-description";
  private static final String DESCRIPTION = "Lorem ipsum";
  private static final MetaTag EMPTY_DESCRIPTION_META_TAG = SimpleMetaTagsFactory.description("");
  private AemContext context = new AemContext();

  private DescriptionMetaTagProvider tested = new DescriptionMetaTagProvider();

  @BeforeEach
  void setUp() {
    context.create().page(WITH_DESCRIPTION, "any-template", ImmutableMap.of("jcr:description", DESCRIPTION));
    context.create().page(WITHOUT_DESCRIPTION);
  }

  @Test
  @DisplayName("For page without a description empty meta tag should be returned")
  void noDescription() {
    context.currentPage(WITHOUT_DESCRIPTION);

    final MetaTag actual = tested.provide(context.request());

    assertThat(actual).isEqualTo(EMPTY_DESCRIPTION_META_TAG);
  }

  @Test
  @DisplayName("For page with a description proper meta tag should be returned")
  void setWithDescription() {
    context.currentPage(WITH_DESCRIPTION);
    final MetaTag actual = tested.provide(context.request());

    assertThat(actual).isEqualTo(SimpleMetaTagsFactory.description(DESCRIPTION));
  }

  @Test
  @DisplayName("Should return empty meta tag when unable to get page manger")
  void noPageManager() {
    SlingHttpServletRequest request = givenRequestWithNoPageManager();

    final MetaTag actual = tested.provide(request);

    assertThat(actual).isEqualTo(EMPTY_DESCRIPTION_META_TAG);
  }

  private SlingHttpServletRequest givenRequestWithNoPageManager() {
    SlingHttpServletRequest request = mock(SlingHttpServletRequest.class);
    ResourceResolver resourceResolver = mock(ResourceResolver.class);
    when(request.getResourceResolver()).thenReturn(resourceResolver);
    when(resourceResolver.adaptTo(PageManager.class)).thenReturn(null);
    return request;
  }
}