package com.aem.tips.core.models.page.impl.seo;

import com.aem.tips.core.models.page.SeoProperties;
import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SeoPropertiesModelImpl implements SeoProperties {

  @Inject
  @Default(booleanValues = false)
  private boolean hideInSearch;

  @Inject
  @Default(values = "")
  private String hideInSearchMetaTagValue;

  @Override
  public boolean hideInSearch() {
    return hideInSearch;
  }

  @Override
  public String hideInSearchMetaTagValue() {
    return hideInSearchMetaTagValue;
  }
}
