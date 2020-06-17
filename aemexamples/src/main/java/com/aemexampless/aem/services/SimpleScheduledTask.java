package com.aemexampless.aem.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Designate(ocd=SimpleScheduledTask.Config.class)
@Component(service=Runnable.class, factory = "com.aemexamples.aem.scheduled.factory")
public class SimpleScheduledTask implements Runnable {

  @ObjectClassDefinition(name="A scheduled task",  description = "...")
  public static @interface Config {

    @AttributeDefinition(name = "Cron-job expression")
    String scheduler_expression() default "*/30 * * * * ?";

    @AttributeDefinition(name = "Concurrent task", description = "Whether or not to schedule this task concurrently")
    boolean scheduler_concurrent() default false;

    @AttributeDefinition(name = "A parameter", description = "..")
    String myParameter() default "";
  }

  private static final Logger LOG = LoggerFactory.getLogger(SimpleScheduledTask.class.getName());

  private String myParameter;

  @Override
  public void run() {
    // LOG.info("Running task scheduled with expression: */30 * * * * ?, with param: {}", myParameter);
  }

  @Activate
  protected void activate(final Config config) {
    myParameter = config.myParameter();
  }

}
