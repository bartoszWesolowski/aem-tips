package com.aem.tips.core.models.page.impl;

import com.aem.tips.core.models.page.PageModel;
import com.aem.tips.core.models.page.SeoPageProperties;
import com.aem.tips.core.models.page.impl.seo.EmptySeoPageProperties;
import com.aem.tips.core.models.page.impl.seo.SeoPagePropertiesModel;
import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PageModelImpl implements PageModel {

  @Inject
  private SeoPagePropertiesModel seo;

  @Override
  public SeoPageProperties getSeoProperties() {
    return seo != null ? seo : EmptySeoPageProperties.empty();
  }
}
