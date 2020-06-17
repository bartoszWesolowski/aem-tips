package com.aemexampless.aem.osgi;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(name="ImmediateExampleService", immediate = true)
public class ImmediateLoadedServiceImpl implements ExampleService {

  private static final Logger LOG = LoggerFactory.getLogger(ImmediateLoadedServiceImpl.class);

  @Activate
  protected void activate() {
    LOG.info("\n\nImmediateExampleService the service\n\n");
    LOG.info("\n\nService loaded right away (immediate = true)\n\n");
  }

  @Override
  public String hello(String from) {
    return String.format("Immediate hello from: %s ", from);
  }
}
