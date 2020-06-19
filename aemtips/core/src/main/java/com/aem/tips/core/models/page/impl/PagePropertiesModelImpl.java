package com.aem.tips.core.models.page.impl;

import com.aem.tips.core.models.page.PagePropertiesModel;
import com.aem.tips.core.models.page.SeoProperties;
import com.aem.tips.core.models.page.impl.seo.EmptySeoProperties;
import com.aem.tips.core.models.page.impl.seo.SeoPropertiesModelImpl;
import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PagePropertiesModelImpl implements PagePropertiesModel {

  @Inject
  private SeoPropertiesModelImpl seo;

  @Override
  public SeoProperties getSeoProperties() {
    return seo != null ? seo : EmptySeoProperties.empty();
  }
}
