package com.aemexampless.aem.components.metatag.api;

import java.util.Map;

public interface MetaTag {

  String getName();

  String getContent();

  boolean isValid();

  Map<String, String> getAttributes();
}
