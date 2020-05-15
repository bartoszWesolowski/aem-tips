package com.aemexampless.aem.listener;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VanityUrlMappings {

  private Map<String, List<String>> vanityUrlMappings = new HashMap<>();

  public void addEntry(String vanityPath, String pagePath) {
    if (!vanityUrlMappings.containsKey(vanityPath)) {
      vanityUrlMappings.put(vanityPath, new ArrayList<>());
    }
    vanityUrlMappings.get(vanityPath).add(pagePath);
  }

  public List<DuplicatedVanityUrlEntry> getDuplicatedEntries() {
    return vanityUrlMappings.entrySet()
      .stream()
      .filter(entry -> entry.getValue().size() > 1)
      .map(entry -> new DuplicatedVanityUrlEntry(entry.getKey(), entry.getValue()))
      .collect(Collectors.toList());
  }
  public static class DuplicatedVanityUrlEntry {
    private String vanityPath;

    private List<String> pagePaths;

    public DuplicatedVanityUrlEntry(String vanityPath, List<String> pagePaths) {
      this.vanityPath = vanityPath;
      this.pagePaths = ImmutableList.copyOf(pagePaths);
    }

    public String getVanityPath() {
      return vanityPath;
    }

    public List<String> getPagePaths() {
      return pagePaths;
    }
  }
}
