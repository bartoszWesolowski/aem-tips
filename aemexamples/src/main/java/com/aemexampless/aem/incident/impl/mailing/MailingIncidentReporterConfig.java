package com.aemexampless.aem.incident.impl.mailing;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="Mailing incident reporter")
public @interface MailingIncidentReporterConfig {

  @AttributeDefinition(name = "Mail used to send incident info")
  String mail() default "default@mail.com";
}
