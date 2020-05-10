package com.aemexampless.aem.osgi;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="Example component configuration")
public @interface ComponentConfiguration {

  @AttributeDefinition(name = "Hello message")
  String helloMessage() default "";
}
