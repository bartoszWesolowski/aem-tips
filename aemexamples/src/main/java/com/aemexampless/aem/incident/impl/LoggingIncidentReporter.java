package com.aemexampless.aem.incident.impl;

import com.aemexampless.aem.incident.api.Incident;
import com.aemexampless.aem.incident.api.IncidentReporter;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class LoggingIncidentReporter implements IncidentReporter {

  private static final Logger LOG = LoggerFactory.getLogger(LoggingIncidentReporter.class);

  @Override
  public void reportIncident(Incident incident) {
    LOG.info("Reporting incident {}. More info: {}", incident.name(), incident.description());
  }
}
