package com.aem.tips.core.models.page;

import com.aem.tips.core.models.page.impl.EmptyPageModelImpl;
import com.aem.tips.core.models.page.impl.PageModelImpl;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import java.util.Optional;
import org.apache.sling.api.SlingHttpServletRequest;

public final class PageModelFactory {

  public static PageModel fromRequest(SlingHttpServletRequest request) {
    final PageManager manager = request.getResource().getResourceResolver().adaptTo(PageManager.class);
    final Optional<PageModel> pageModel = Optional.ofNullable(manager)
        .map(pageManager -> pageManager.getContainingPage(request.getResource()))
        .map(Page::getContentResource)
        .map(jcrContent -> jcrContent.adaptTo(PageModelImpl.class));
    return pageModel
        .orElse(EmptyPageModelImpl.empty());
  }

  private PageModelFactory() {
    // no instance for that class
  }
}
