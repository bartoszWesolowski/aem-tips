package com.aemexampless.aem.incident.impl;

import com.aemexampless.aem.incident.impl.LoggingIncidentReporterWitRequiredConfig.LoggingIncidentReporterRequiredConfig;
import com.aemexampless.aem.incident.api.Incident;
import com.aemexampless.aem.incident.api.IncidentReporter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = LoggingIncidentReporterRequiredConfig.class)
public class LoggingIncidentReporterWitRequiredConfig implements IncidentReporter {

  @ObjectClassDefinition(name="Logging incident reporter")
  public static @interface LoggingIncidentReporterRequiredConfig {

    @AttributeDefinition(name = "Additional message")
    String additionalMessage();
  }

  private static final Logger LOG = LoggerFactory.getLogger(
    LoggingIncidentReporterWitRequiredConfig.class);


  @Override
  public void reportIncident(Incident incident) {
    LOG.info("Reporting incident when config present: {}. More info: {}", incident.name(), incident.description());
  }
}
