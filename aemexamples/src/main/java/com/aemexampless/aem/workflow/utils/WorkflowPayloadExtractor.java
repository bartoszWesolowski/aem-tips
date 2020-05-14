package com.aemexampless.aem.workflow.utils;

import com.adobe.granite.workflow.PayloadMap;
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import java.util.Optional;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = WorkflowPayloadExtractor.class)
public class WorkflowPayloadExtractor {

  @Reference
  private ResourceResolverFactory factory;

  public String getPayloadResourcePath(WorkItem workItem) throws WorkflowException {
    return Optional.ofNullable(workItem)
      .map(WorkItem::getWorkflowData)
      .filter(this::hasPathPayload)
      .map(WorkflowData::getPayload)
      .map(Object::toString)
      .orElseThrow(() -> new WorkflowException("Failed to get Workflow payload path"));
  }

  public Resource getPayloadResource(WorkItem workItem, ResourceResolver resourceResolver)
    throws WorkflowException {
    String payloadPath = getPayloadResourcePath(workItem);
    return Optional.ofNullable(resourceResolver.getResource(payloadPath))
      .orElseThrow(() -> new WorkflowException(
        "Can not find workflow payload resource for path: " + payloadPath));
  }

  private boolean hasPathPayload(WorkflowData workflowData) {
    return PayloadMap.TYPE_JCR_PATH.equals(workflowData.getPayloadType());
  }
}
