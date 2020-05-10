package com.aemexampless.aem.incident;

import com.aemexampless.aem.incident.impl.SimpleIncident;
import java.util.Date;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Designate(ocd = IncidentReportingDemoCall.Config.class)
@Component(service = Runnable.class)
public class IncidentReportingDemoCall implements Runnable {

  @ObjectClassDefinition(name = "A scheduled task", description = "...")
  public static @interface Config {

    @AttributeDefinition(name = "Cron-job expression")
    String scheduler_expression() default "*/30 * * * * ?";

    @AttributeDefinition(name = "Concurrent task", description = "Whether or not to schedule this task concurrently")
    boolean scheduler_concurrent() default false;

    @AttributeDefinition(name = "A parameter", description = "..")
    String myParameter() default "";
  }

  private static final Logger LOG = LoggerFactory
    .getLogger(IncidentReportingDemoCall.class.getName());

  private String myParameter;

  @Reference
  private IncidentReportingService incidentReportingService;

  @Override
  public void run() {
    incidentReportingService
      .reportIncident(new SimpleIncident("Demo incident", "Date: " + new Date().toString()));
    LOG.info("Running task scheduled with expression: */30 * * * * ?, with param: {}", myParameter);
  }

  @Activate
  protected void activate(final Config config) {
    myParameter = config.myParameter();
  }

}
