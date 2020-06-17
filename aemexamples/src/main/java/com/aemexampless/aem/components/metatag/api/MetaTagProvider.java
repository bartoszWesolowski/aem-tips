package com.aemexampless.aem.components.metatag.api;

import java.util.List;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;

public interface MetaTagProvider {

  List<MetaTag> createMetaTags(Resource resource, SlingHttpServletRequest request);
}
