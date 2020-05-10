package com.aemexampless.aem.osgi;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = ComponentConfiguration.class)
public class ServiceConsumerWithRequiredConfiguration {

  private static final Logger LOG = LoggerFactory.getLogger(
    ServiceConsumerWithRequiredConfiguration.class);

  @Reference(name = "LazyExampleService", scope = ReferenceScope.PROTOTYPE_REQUIRED)
  private ExampleService exampleService;

  private String helloMessage;

  @Activate
  protected void activate(ComponentConfiguration configuration) {
    LOG.info("Activating service consumer");
    this.helloMessage = configuration.helloMessage();
    LOG.info(exampleService.hello(this.getClass().getName() + " " + helloMessage));
  }

}
