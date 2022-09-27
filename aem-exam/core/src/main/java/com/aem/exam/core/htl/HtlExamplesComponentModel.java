package com.aem.exam.core.htl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class HtlExamplesComponentModel {

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
}
