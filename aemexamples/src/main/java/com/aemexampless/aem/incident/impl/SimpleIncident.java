package com.aemexampless.aem.incident.impl;

import com.aemexampless.aem.incident.api.Incident;

public class SimpleIncident implements Incident {

  private String name;

  private String description;

  public SimpleIncident(String name, String description) {
    this.name = name;
    this.description = description;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public String description() {
    return description;
  }
}
