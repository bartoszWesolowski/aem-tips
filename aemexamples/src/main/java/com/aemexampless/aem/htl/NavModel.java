package com.aemexampless.aem.htl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavModel {

  @Inject
  private String depth;

  @Inject
  private boolean isVisible;

  public Map<String, Object> properties() {
    return ImmutableMap.of(
      "attr1", "attr1 value",
      "attrWithTrue", true,
      "attrWithFalse", false,
      "array-attr", ImmutableList.of(1, 2, 3),
      "array-attr-str", ImmutableList.of("1", "2", "3")
    );
  }

  public List<Object> list() {
   return ImmutableList.of(0, "1", "2", "3", 4, 5, 6, 7);
  }

  public String getDepth() {
    return depth;
  }

  public boolean isVisible() {
    return isVisible;
  }
}
