package com.aem.tips.core.models.page;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.aem.tips.core.models.page.impl.PagePropertiesModelImpl;
import com.aem.tips.core.models.page.impl.seo.SeoPropertiesModelImpl;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AemContextExtension.class)
class PagePropertiesModelFactoryTest {

  private static final String PAGE_WITHOUT_SEO_PROPS = "/content/test/no-seo";
  private static final String PAGE_WITH_SEO_PROPS = "/content/test/with-seo";

  private final AemContext context = new AemContext();

  private PageModelFactory tested = new PageModelFactory();

  @BeforeEach
  void setUp() {
    context.load().json("/model/page/page-without-seo-properties.json", PAGE_WITHOUT_SEO_PROPS);
    context.load().json("/model/page/page-with-seo-properties.json", PAGE_WITH_SEO_PROPS);
    context.addModelsForClasses(PagePropertiesModelImpl.class, SeoPropertiesModelImpl.class);
  }

  @Test
  @DisplayName("Should return empty values for a page without seo properties set.")
  void shouldReturnEmptySeo() {
    context.currentPage(PAGE_WITHOUT_SEO_PROPS);

    final PagePropertiesModel actual = tested.fromRequest(context.request());

    final SeoProperties seoProperties = actual.getSeoProperties();
    assertThat(seoProperties).isNotNull();
    assertThat(seoProperties.hideInSearch()).isFalse();
    assertThat(seoProperties.hideInSearchMetaTagValue()).isEmpty();
  }

  @Test
  @DisplayName("Should return return actual data when seo resource is available")
  void name() {
    context.currentPage(PAGE_WITH_SEO_PROPS);

    final PagePropertiesModel actual = tested.fromRequest(context.request());

    final SeoProperties seoProperties = actual.getSeoProperties();
    assertThat(seoProperties).isNotNull();
    assertThat(seoProperties.hideInSearch()).isTrue();
    assertThat(seoProperties.hideInSearchMetaTagValue()).isEqualTo("noindex, nofollow");
  }
}