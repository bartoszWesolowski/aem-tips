package com.aemexampless.aem.htl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class)
public class HtlExamplesComponentModel {

  private static final Logger LOG = LoggerFactory.getLogger(HtlExamplesComponentModel.class);

  public Map<String, Object> attributes() {
    return ImmutableMap.of(
      "attr1", "attr1 value",
      "attr2", true,
      "attr3", false,
      "attr-list", ImmutableList.of("v1", "v2")
    );
  }

  public List<String> items() {
    return ImmutableList.of(
      "Item first",
      "Item second",
      "Item third"
    );
  }

  public String text() {
    return "Text from sling model";
  }

  @PostConstruct
  protected void init() {
    LOG.error("Error rom component");
  }
}
