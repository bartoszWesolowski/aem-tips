package com.aemexampless.aem.components.page;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class PageModel {

  @Inject
  @Named("sling:vanityPath")
  private List<String> vanityPaths;

  @PostConstruct
  protected void init() {
    vanityPaths = Optional.ofNullable(vanityPaths)
      .orElse(Collections.emptyList());
  }

  public List<String> getVanityPaths() {
    return vanityPaths;
  }
}
