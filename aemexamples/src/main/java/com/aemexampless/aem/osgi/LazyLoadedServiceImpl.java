package com.aemexampless.aem.osgi;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(name="LazyExampleService")
public class LazyLoadedServiceImpl implements ExampleService {

  private static final Logger LOG = LoggerFactory.getLogger(LazyLoadedServiceImpl.class);

  @Activate
  protected void activate() {
    LOG.info("\n\nActivating the service\n\n");
    LOG.info("\n\nService loaded because it is consumed by component/service\n\n");
  }

  @Override
  public String hello(String from) {
    return String.format("Hello from: %s ", from);
  }
}
