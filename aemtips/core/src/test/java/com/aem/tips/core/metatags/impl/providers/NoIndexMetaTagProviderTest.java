package com.aem.tips.core.metatags.impl.providers;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.aem.tips.core.metatags.api.MetaTag;
import com.aem.tips.core.metatags.api.MetaTagsProvider;
import com.aem.tips.core.metatags.impl.SimpleMetaTagsFactory;
import com.aem.tips.core.models.page.PageModelFactory;
import com.aem.tips.core.models.page.PagePropertiesModel;
import com.aem.tips.core.models.page.SeoProperties;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AemContextExtension.class)
class NoIndexMetaTagProviderTest {

  private MetaTagsProvider tested = new NoIndexMetaTagProvider();

  private PageModelFactory pageModelFactory = mock(PageModelFactory.class);

  private AemContext context = new AemContext();

  @BeforeEach
  void setUp() {
    context.registerService(PageModelFactory.class, pageModelFactory);
    context.registerInjectActivateService(tested);
  }

  @Test
  @DisplayName("Should return empty meta tag given page is not hidden from search empty meta tag should be returned")
  void pageNotHidden() {
    when(pageModelFactory.fromRequest(context.request()))
        .thenReturn(new PageProperties(false, "all values ignored"));

    final MetaTag provided = tested.provide(context.request());

    assertThat(provided).isEqualTo(SimpleMetaTagsFactory.robots(""));
  }

  @Test
  @DisplayName("Should return empty meta tag given page is hidden from search but does not have meta tag value set")
  void hiddenWithout() {
    when(pageModelFactory.fromRequest(context.request()))
        .thenReturn(new PageProperties(true, ""));

    final MetaTag provided = tested.provide(context.request());

    assertThat(provided).isEqualTo(SimpleMetaTagsFactory.robots(""));
  }

  @Test
  @DisplayName("Should return meta tag with correct value when page is hidden from search and has tag value set")
  void hiddenWithTag() {
    when(pageModelFactory.fromRequest(context.request()))
        .thenReturn(new PageProperties(true, "noindex"));

    final MetaTag provided = tested.provide(context.request());

    assertThat(provided).isEqualTo(SimpleMetaTagsFactory.robots("noindex"));
  }

  private static class PageProperties implements PagePropertiesModel {

    private boolean hideInSearch;

    private String tagValue;

    PageProperties(boolean hideInSearch, String tagValue) {
      this.hideInSearch = hideInSearch;
      this.tagValue = tagValue;
    }

    @Override
    public SeoProperties getSeoProperties() {
      return new SeoProperties() {
        @Override
        public boolean hideInSearch() {
          return hideInSearch;
        }

        @Override
        public String hideInSearchMetaTagValue() {
          return tagValue;
        }
      };
    }
  }
}