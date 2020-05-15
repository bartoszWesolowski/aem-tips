package com.aemexampless.aem.listener.impl;

import com.aemexampless.aem.listener.DuplicatedVanityUrlErrorHandler;
import com.aemexampless.aem.listener.VanityUrlMappings.DuplicatedVanityUrlEntry;
import java.util.List;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class LoggingDuplicatedVanityErrorHandler implements DuplicatedVanityUrlErrorHandler {

  private static final Logger LOG = LoggerFactory.getLogger(LoggingDuplicatedVanityErrorHandler.class);
  @Override
  public void handledDuplicates(
    List<DuplicatedVanityUrlEntry> duplicated) {
    duplicated.forEach(this::logError);
  }

  private void logError(DuplicatedVanityUrlEntry entry) {
    LOG.error("Duplicated entries for sling vanity url {} found: {}",
      entry.getVanityPath(), entry.getPagePaths());
  }
}
