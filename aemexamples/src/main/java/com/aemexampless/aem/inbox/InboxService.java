package com.aemexampless.aem.inbox;

import com.adobe.granite.taskmanagement.Task;
import com.adobe.granite.taskmanagement.TaskManager;
import com.adobe.granite.taskmanagement.TaskManagerException;
import com.adobe.granite.taskmanagement.TaskManagerFactory;
import java.util.Optional;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = InboxService.class)
public class InboxService {

  private static final Logger LOG = LoggerFactory.getLogger(InboxService.class);

  public Optional<Task> tryToCreateInboxItem(InboxTaskBody inboxTaskBody, ResourceResolver rr) throws TaskManagerException {
    TaskManager taskManager = rr.adaptTo(TaskManager.class);
    return Optional.ofNullable(createTaskSafely(inboxTaskBody, taskManager));
  }

  private Task toTask(InboxTaskBody taskPayload, TaskManager taskManager) throws TaskManagerException {
    if (taskManager == null) {
      throw new TaskManagerException("Failed to create task manager from resource resolver");
    }
    TaskManagerFactory factory = taskManager.getTaskManagerFactory();
    Task task = factory.newTask(taskPayload.getType());
    task.setCurrentAssignee(taskPayload.getAssignee());
    task.setContentPath(taskPayload.getContentPath());
    task.setDescription(taskPayload.getDescription());
    task.setName(taskPayload.getTitle());
    task.setPriority(taskPayload.getPriority());
    taskPayload.getProperties()
      .forEach(task::setProperty);
    return taskManager.createTask(task);
  }

  private Task createTaskSafely(InboxTaskBody body, TaskManager taskManager) {
    try {
      return toTask(body, taskManager);
    } catch (TaskManagerException e) {
      LOG.error("Failed to create task {}", body, e);
    }
    return null;
  }
}
