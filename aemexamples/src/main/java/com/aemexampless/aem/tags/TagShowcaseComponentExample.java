package com.aemexampless.aem.tags;

import com.day.cq.tagging.TagManager;
import java.util.Optional;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, name = "Tag Showcase Component")
public class TagShowcaseComponentExample {

  private static final Logger LOG = LoggerFactory.getLogger(TagShowcaseComponentExample.class);

  @Reference
  private ResourceResolverFactory factory;

  private String tagToFind = "aem-examples:quiz/development,aem-examples:quiz/htl";

  @Activate
  private void activate() {
    try (ResourceResolver rr = factory.getServiceResourceResolver(null)) {
      Optional.ofNullable(rr.adaptTo(TagManager.class))
        .ifPresent(this::tagExampleActions);
    } catch (LoginException e) {
      LOG.error("Failed to get resource resolver", e);
    }
  }

  private void tagExampleActions(TagManager tagManager) {
    tagManager.find(tagToFind).forEachRemaining(this::printResource);
  }

  private void printResource(Resource resource) {
    LOG.info("Found resource with given tag {}, path: {}", tagToFind, resource.getPath());
  }
}
