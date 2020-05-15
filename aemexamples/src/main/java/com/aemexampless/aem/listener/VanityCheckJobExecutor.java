package com.aemexampless.aem.listener;

import static com.aemexampless.aem.listener.VanityCheckJobExecutor.TOPIC;

import com.aemexampless.aem.components.page.PageModel;
import com.aemexampless.aem.inbox.InboxService;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import javax.jcr.query.Query;
import org.apache.jackrabbit.JcrConstants;
import org.apache.jackrabbit.spi.commons.query.QueryConstants;
import org.apache.jackrabbit.spi.commons.query.sql.JCRSQLQueryBuilder;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobExecutionContext;
import org.apache.sling.event.jobs.consumer.JobExecutionResult;
import org.apache.sling.event.jobs.consumer.JobExecutor;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(property = {
  JobExecutor.PROPERTY_TOPICS + "=" + TOPIC
})
public class VanityCheckJobExecutor implements JobExecutor {

  public static final String TOPIC = "aemexamples/vanity/check";

  private static final Logger LOG = LoggerFactory.getLogger(VanityCheckJobExecutor.class);

  private static final String QUERY = "SELECT * FROM [cq:PageContent] AS s WHERE ISDESCENDANTNODE([/content/aemexamples])";

  @Reference
  private ResourceResolverFactory factory;

  @Reference(service = DuplicatedVanityUrlErrorHandler.class,
  cardinality = ReferenceCardinality.MULTIPLE,
    policyOption = ReferencePolicyOption.RELUCTANT, policy = ReferencePolicy.DYNAMIC)
  protected volatile Collection<DuplicatedVanityUrlErrorHandler> handlers = ConcurrentHashMap.newKeySet();

  @Override
  public JobExecutionResult process(Job job, JobExecutionContext jobExecutionContext) {
    try (ResourceResolver rr = factory.getServiceResourceResolver(null)) {
      VanityUrlMappings vanityUrlMappings = extractVanityMappings(rr);
      handlers.forEach(handler -> handler.handledDuplicates(vanityUrlMappings.getDuplicatedEntries()));
    } catch (LoginException e) {
      LOG.error("Failed to get service resource resolver", e);
    }
    return jobExecutionContext.result().succeeded();
  }

  private VanityUrlMappings extractVanityMappings(ResourceResolver rr) {
    Iterator<Resource> resources = rr.findResources(QUERY, Query.JCR_SQL2);
    VanityUrlMappings vanityUrlMappings = new VanityUrlMappings();
    while (resources.hasNext()) {
      Resource jcrContent = resources.next();
      String path = Optional.ofNullable(jcrContent)
        .map(Resource::getParent)
        .map(Resource::getPath)
        .orElse("");
      getVanityUrls(jcrContent)
        .forEach(vanityUrl -> vanityUrlMappings.addEntry(vanityUrl, path));
    }
    return vanityUrlMappings;
  }

  private List<String> getVanityUrls(Resource jcrContent) {
    return Optional.ofNullable(jcrContent)
      .map(r -> r.adaptTo(PageModel.class))
      .map(PageModel::getVanityPaths)
      .orElse(Collections.emptyList());
  }

  private void doStep() {
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      LOG.error("", e);
    }
  }
}
