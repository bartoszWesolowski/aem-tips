package com.aemexampless.aem.listener;

import static com.aemexampless.aem.listener.VanityCheckJobExecutor.TOPIC;

import com.day.cq.wcm.api.PageManager;
import com.day.crx.JcrConstants;
import com.google.common.collect.ImmutableList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChange.ChangeType;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.apache.sling.event.jobs.JobManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true,
property = {
  ResourceChangeListener.CHANGES + " =CHANGED",
  ResourceChangeListener.CHANGES + " =ADDED",
  ResourceChangeListener.PATHS + "=/content",
  ResourceChangeListener.PROPERTY_NAMES_HINT + "=sling:vanityPath"
})
public class VanityResourceChangeListener implements ResourceChangeListener {

  private static final Logger LOG = LoggerFactory.getLogger(VanityResourceChangeListener.class);

  private static final List<ChangeType> SUPPORTED_CHANGES = ImmutableList.of(
    ChangeType.CHANGED,
    ChangeType.ADDED
  );

  @Reference
  private ResourceResolverFactory factory;

  @Reference
  private JobManager jobManager;

  @Override
  public void onChange(List<ResourceChange> changes) {
    Set<String> changedPages = getChangedPages(changes);
    try(ResourceResolver rr = factory.getServiceResourceResolver(null)) {
      if (containsPageWithVanityUrl(changedPages, rr)) {
        LOG.info("Triggering job to check vanity properties.");
        jobManager.addJob(TOPIC, Collections.emptyMap());
      };
    } catch (LoginException e) {
      LOG.error("Failed to ger service resource resolver", e);
    }
    LOG.info("Vanity changed for pages: {}",  changedPages);
  }

  private boolean containsPageWithVanityUrl(Set<String> changedPages, ResourceResolver rr) {
    PageManager pageManager = rr.adaptTo(PageManager.class);
    List<String> withVanityUrl = changedPages.stream()
      .filter(page -> StringUtils.isNotEmpty(this.getVanityUrl(pageManager, page)))
      .collect(Collectors.toList());
    return !withVanityUrl.isEmpty();
  }

  private String getVanityUrl(PageManager pageManager, String pagePath) {
    return Optional.ofNullable(pageManager).
      map(pm -> pageManager.getPage(pagePath))
      .map(page -> page.getVanityUrl())
      .orElse("");
  }

  private Set<String> getChangedPages(List<ResourceChange> changes) {
    List<ResourceChange> collect = changes.stream()
      .filter(this::isValidChange)
      .collect(Collectors.toList());

    return changes.stream()
      .map(this::getPagePath)
      .collect(Collectors.toSet());
  }

  private boolean isValidChange(ResourceChange resourceChange) {
    return SUPPORTED_CHANGES.stream().anyMatch(supportedType -> supportedType == resourceChange.getType());
  }

  private String getPagePath(ResourceChange resourceChange) {
    return StringUtils.substringBeforeLast(resourceChange.getPath(), JcrConstants.JCR_CONTENT);
  }
}
