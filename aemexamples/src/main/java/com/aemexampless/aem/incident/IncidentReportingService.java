package com.aemexampless.aem.incident;

import com.aemexampless.aem.incident.api.Incident;
import com.aemexampless.aem.incident.api.IncidentReporter;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.FieldOption;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, service = IncidentReportingService.class)
public class IncidentReportingService {

  private static final Logger LOG = LoggerFactory.getLogger(IncidentReportingService.class);

  // With dynamic greedy reference this component will be updated (wi
  // new service will become available
  @Reference(cardinality = ReferenceCardinality.MULTIPLE,
    fieldOption = FieldOption.UPDATE,
    updated = "serviceUpdated",
    policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY)
  private Set<IncidentReporter> incidentReporters = ConcurrentHashMap.newKeySet();

  @Activate
  public void activate() {
    LOG.info("Activating");
  }

  public void reportIncident(Incident incident) {
    incidentReporters.forEach(reporter -> reporter.reportIncident(incident));
  }

  public void serviceUpdated(ServiceReference<IncidentReporter> serviceReference) {
    LOG.info("Service updated");
  }
}
