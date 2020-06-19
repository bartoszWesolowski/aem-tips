package com.aem.tips.core.metatags.impl.providers;

import com.aem.tips.core.metatags.api.MetaTag;
import com.aem.tips.core.metatags.api.MetaTagsProvider;
import com.aem.tips.core.metatags.impl.SimpleMetaTagsFactory;
import com.aem.tips.core.models.page.PageModelFactory;
import com.aem.tips.core.models.page.PagePropertiesModel;
import com.aem.tips.core.models.page.SeoProperties;
import org.apache.sling.api.SlingHttpServletRequest;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = MetaTagsProvider.class, immediate = true)
public class RobotsMetaTagProvider implements MetaTagsProvider {

  @Reference
  private PageModelFactory pageModelFactory;

  @Override
  public MetaTag provide(SlingHttpServletRequest request) {
    PagePropertiesModel pageModel = pageModelFactory.fromRequest(request);
    final SeoProperties seo = pageModel.getSeoProperties();
    String content = seo.hideInSearch() ? seo.hideInSearchMetaTagValue() : "";
    return SimpleMetaTagsFactory.robots(content);
  }
}
