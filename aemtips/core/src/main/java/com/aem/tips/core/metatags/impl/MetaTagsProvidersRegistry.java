package com.aem.tips.core.metatags.impl;

import com.aem.tips.core.metatags.api.MetaTag;
import com.aem.tips.core.metatags.api.MetaTagsProvider;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.apache.sling.api.SlingHttpServletRequest;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.FieldOption;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = MetaTagsProvidersRegistry.class, immediate = true)
public class MetaTagsProvidersRegistry {

  private static final Logger LOG =
      LoggerFactory.getLogger(MetaTagsProvidersRegistry.class);

  @Reference(
      cardinality = ReferenceCardinality.MULTIPLE,
      policy = ReferencePolicy.DYNAMIC,
      service = MetaTagsProvider.class,
      bind = "registerProvider",
      unbind = "unregisterProvider",
      fieldOption = FieldOption.UPDATE
  )
  private volatile Set<MetaTagsProvider> providers = ConcurrentHashMap.newKeySet();

  public List<MetaTag> createMetaTags(SlingHttpServletRequest request) {
    List<MetaTag> metaTags = new LinkedList<>();
    providers.forEach(provider -> metaTags.addAll(getSafely(provider, request)));
    return metaTags;
  }

  private List<MetaTag> getSafely(
      MetaTagsProvider provider,
      SlingHttpServletRequest request) {
    try {
      return provider.provideAll(request)
          .stream()
          .filter(MetaTag::isValid)
          .collect(Collectors.toList());
    } catch (Exception e) {
      LOG.warn("Failed to create meta tags with provider [{}] - exception ignored.", provider, e);
    }
    return Collections.emptyList();
  }

  public void registerProvider(MetaTagsProvider provider) {
    LOG.info("Registering new meta tags provider: [{}]", provider);
    providers.add(provider);
  }

  public void unregisterProvider(MetaTagsProvider provider) {
    LOG.info("Unregistering meta tags provider: [{}]", provider);
    providers.remove(provider);
  }

}
