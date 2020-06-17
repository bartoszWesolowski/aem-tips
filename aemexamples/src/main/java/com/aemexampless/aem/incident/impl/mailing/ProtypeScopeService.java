package com.aemexampless.aem.incident.impl.mailing;

import java.util.UUID;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

@Component(immediate = true, service = ProtypeScopeService.class)
public class ProtypeScopeService {

  private final UUID uuid = UUID.randomUUID();

  public String logId() {
    return String.valueOf(this.hashCode()) + uuid.toString();
  }
}
