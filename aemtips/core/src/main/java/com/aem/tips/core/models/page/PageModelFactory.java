package com.aem.tips.core.models.page;

import com.aem.tips.core.models.page.impl.EmptyPagePropertiesModel;
import com.aem.tips.core.models.page.impl.PagePropertiesModelImpl;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import java.util.Optional;
import org.apache.sling.api.SlingHttpServletRequest;
import org.osgi.service.component.annotations.Component;

@Component(service = PageModelFactory.class)
public class PageModelFactory {

  public PagePropertiesModel fromRequest(SlingHttpServletRequest request) {
    final PageManager manager = request.getResourceResolver().adaptTo(PageManager.class);
    final Optional<PagePropertiesModel> pageModel = Optional.ofNullable(manager)
        .map(pageManager -> pageManager.getContainingPage(request.getResource()))
        .map(Page::getContentResource)
        .map(jcrContent -> jcrContent.adaptTo(PagePropertiesModelImpl.class));
    return pageModel
        .orElse(EmptyPagePropertiesModel.empty());
  }
}
