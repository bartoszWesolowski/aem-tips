package com.aemexampless.aem.servlet;

import com.day.cq.wcm.api.NameConstants;
import com.google.common.collect.ImmutableMap;
import com.google.common.net.MediaType;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Map;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true,
  service = Servlet.class,
  property = {
    "sling.servlet.extensions=json",
    "sling.servlet.selectors=hello",
    "sling.servlet.resourceTypes=" + "aemexamples/components/servletComponent",
    "sling.servlet.paths=" + "/bin/example/servlet"
  })
public class ExampleServlet extends SlingSafeMethodsServlet {

  @Override
  protected void doGet(SlingHttpServletRequest request,
    SlingHttpServletResponse response) throws ServletException, IOException {
    Map<String, String> result = ImmutableMap.of("message", "Hello World!");
    response.setContentType(MediaType.JSON_UTF_8.toString());
    response.getWriter().write(new Gson().toJson(result));
  }
}
