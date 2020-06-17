package com.aemexampless.aem.workflow.process;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.aemexampless.aem.workflow.utils.WorkflowPayloadExtractor;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;
import org.apache.commons.lang3.StringUtils;
import org.apache.jackrabbit.spi.commons.batch.Operations.SetPrimaryType;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true,
  property = {
    Constants.SERVICE_DESCRIPTION + "=Update asset jcr content",
    Constants.SERVICE_VENDOR + "=AEM Examples",
    "process.label=" + "Update asset jcr content with Node API"
  })
public class AddJcrContentNodeProperty implements WorkflowProcess {

  private static final Logger LOG = LoggerFactory.getLogger(AddJcrContentNodeProperty.class);
  @Reference
  private ResourceResolverFactory factory;

  @Reference
  private WorkflowPayloadExtractor workflowPayloadExtractor;

  @Override
  public void execute(WorkItem workItem,
    WorkflowSession workflowSession,
    MetaDataMap metaDataMap) throws WorkflowException {
    String propValue = StringUtils.defaultString(metaDataMap.get("PROCESS_ARGS", String.class), "DEFAULT PROP VALUE");
    try(ResourceResolver rr = factory.getServiceResourceResolver(null)) {
      Resource payloadResource = workflowPayloadExtractor.getPayloadResource(workItem, rr);
      Resource jcrContent = payloadResource.getChild("jcr:content");
      Node node = jcrContent.adaptTo(Node.class);
      if (node != null) {
        PropertyIterator properties = node.getProperties();
        while (properties.hasNext()) {
          Property property = properties.nextProperty();
          LOG.info("Node property: {} -> {}", property.getName(), property.getValue().getString());
        }
      }

      Session session = rr.adaptTo(Session.class);
      if (session != null) {
        Node rootNode = session.getRootNode();
        Node newNode = rootNode.addNode("newNode");
        newNode.setProperty("xyz", "xyz-value");
        newNode.setPrimaryType("nt:unstructured");
        session.save();
      }
    } catch (LoginException e) {
      throw new WorkflowException("Failed to create session to get the resource payload", e);
    } catch (RepositoryException e) {
     throw new WorkflowException("", e);
    }
  }
}
