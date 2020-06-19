package com.aem.tips.core.models.metatags;

import com.aem.tips.core.metatags.api.MetaTag;
import com.aem.tips.core.metatags.impl.MetaTagsProvidersRegistry;
import java.util.List;
import javax.inject.Inject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MetaTagsComponentModel {

  @Inject
  private SlingHttpServletRequest request;

  @OSGiService
  private MetaTagsProvidersRegistry registry;

  public List<MetaTag> getMetaTags() {
    return registry.createMetaTags(request);
  }

}
