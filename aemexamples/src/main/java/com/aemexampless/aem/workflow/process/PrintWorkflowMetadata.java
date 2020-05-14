package com.aemexampless.aem.workflow.process;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(immediate = true,
  property = {
    Constants.SERVICE_DESCRIPTION + "=Prints all props in workflow metadata",
    Constants.SERVICE_VENDOR + "=AEM Examples",
    "process.label=" + "Print basic info about workflow to log file"
  })
public class PrintWorkflowMetadata implements WorkflowProcess {

  private static final Logger LOG = LoggerFactory.getLogger(PrintWorkflowMetadata.class);

  @Override
  public void execute(WorkItem workItem,
    WorkflowSession workflowSession,
    MetaDataMap metaDataMap) throws WorkflowException {
    LOG.info("Executing workflow with id: {}", workItem.getWorkflow().getId());
    workItem.getWorkflowData().getMetaDataMap()
      .forEach((k,v ) -> LOG.info("Workflow metadata prop: {} = {}, workflow payload: {}", k, v, workItem.getWorkflowData().getPayload()));

    LOG.info("Workflow step properties from dialog (workflow model)");
    metaDataMap.entrySet().forEach((e) -> LOG.info("Worflow props from workflow model step dialog: {} -> {}", e.getKey(), e.getValue()));
  }
}

