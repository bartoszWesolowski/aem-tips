package com.aem.tips.core.metatags.impl.providers;

import com.aem.tips.core.metatags.api.MetaTag;
import com.aem.tips.core.metatags.api.MetaTagsProvider;
import com.aem.tips.core.metatags.impl.SimpleMetaTagsFactory;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.osgi.service.component.annotations.Component;

@Component(service = MetaTagsProvider.class, immediate = true)
public class DescriptionMetaTagProvider implements MetaTagsProvider {

  @Override
  public MetaTag provide(SlingHttpServletRequest request) {
    PageManager pageManager = request.getResourceResolver().adaptTo(PageManager.class);
    String description = Optional.ofNullable(pageManager)
        .map(pm -> pm.getContainingPage(request.getResource()))
        .map(Page::getDescription)
        .orElse(StringUtils.EMPTY);
    return SimpleMetaTagsFactory.description(description);
  }
}
