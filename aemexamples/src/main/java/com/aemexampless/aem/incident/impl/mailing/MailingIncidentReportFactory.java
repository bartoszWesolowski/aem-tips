package com.aemexampless.aem.incident.impl.mailing;

import com.aemexampless.aem.incident.api.Incident;
import com.aemexampless.aem.incident.api.IncidentReporter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)
@Designate(ocd = MailingIncidentReporterConfig.class, factory = true)
public class MailingIncidentReportFactory implements IncidentReporter {

  private static final Logger LOG = LoggerFactory.getLogger(MailingIncidentReportFactory.class);

  private String mail;

  @Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
  private ProtypeScopeService protypeScopeService;

  protected void activate(MailingIncidentReporterConfig config) {
    this.mail = config.mail();
  }
  @Override
  public void reportIncident(Incident incident) {
    LOG.info("sending email to {} for incident {}. More info: {}", mail, incident.name(), incident.description());
    LOG.info("Prototype service: {}", protypeScopeService.logId());
  }
}
