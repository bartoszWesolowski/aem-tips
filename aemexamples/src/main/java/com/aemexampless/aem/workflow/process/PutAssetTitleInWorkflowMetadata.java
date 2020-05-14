package com.aemexampless.aem.workflow.process;

import com.adobe.cq.commerce.common.ValueMapDecorator;
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.aemexampless.aem.workflow.utils.WorkflowPayloadExtractor;
import java.util.Collections;
import java.util.Optional;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true,
  property = {
    Constants.SERVICE_DESCRIPTION + "=Copies asset properties to workflow metadata",
    Constants.SERVICE_VENDOR + "=AEM Examples",
    "process.label=" + "Save asset properties in workflow metadata"
  })
public class PutAssetTitleInWorkflowMetadata implements WorkflowProcess {

  @Reference
  private WorkflowPayloadExtractor workflowPayloadExtractor;

  @Reference
  private ResourceResolverFactory factory;

  @Override
  public void execute(WorkItem workItem,
    WorkflowSession workflowSession,
    MetaDataMap metaDataMap) throws WorkflowException {
    try(ResourceResolver rr = factory.getServiceResourceResolver(null)) {
      putPropsToMetadata(workItem, rr);
    } catch (LoginException e) {
      throw new WorkflowException("Failed to create session to get the resource payload", e);
    }
  }

  private void putPropsToMetadata(WorkItem workItem, ResourceResolver rr) throws WorkflowException {
    Resource payloadResource = workflowPayloadExtractor.getPayloadResource(workItem, rr);
    ValueMap valueMap = Optional.ofNullable(payloadResource)
      .map(r -> r.getChild("jcr:content/metadata"))
      .map(Resource::getValueMap)
      .orElse(new ValueMapDecorator(Collections.emptyMap()));
    valueMap.forEach((k, v) -> workItem.getWorkflowData().getMetaDataMap().put("from_resource " + k.replace(":", "_"), v));
  }
}

