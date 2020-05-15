package com.aemexampless.aem.inbox;

import com.adobe.granite.workflow.exec.InboxItem.Priority;
import java.util.Collections;
import java.util.Map;

public class InboxTaskBody {

  private String assignee;
  private String contentPath;
  private String description;
  private String title;
  private String type;
  private Priority priority = Priority.MEDIUM;
  private Map<String, Object> properties = Collections.emptyMap();

  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  public String getContentPath() {
    return contentPath;
  }

  public void setContentPath(String contentPath) {
    this.contentPath = contentPath;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Priority getPriority() {
    return priority;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }

  public Map<String, Object> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, Object> properties) {
    this.properties = properties;
  }
}
