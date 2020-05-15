package com.aemexampless.aem.components.core;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.internal.models.v1.TitleImpl;
import com.adobe.cq.wcm.core.components.models.Title;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.via.ResourceSuperType;

@Model(adaptables = SlingHttpServletRequest.class, resourceType = "aemexamples/components/core/title",
  adapters = {Title.class, ComponentExporter.class})
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class TitleComponentImpl implements Title {

  private Boolean upperCase = true;

  @Self
  @Via(type = ResourceSuperType.class)
  private Title title;

  @Override
  public String getText() {
    String text = title.getText();
    if (upperCase) {
      text = StringUtils.upperCase(text);
    }
    return text;
  }

  @Override
  public String getType() {
    return title.getType();
  }

  @Override
  public String getLinkURL() {
    return title.getLinkURL();
  }

  @Override
  public boolean isLinkDisabled() {
    return title.isLinkDisabled();
  }

  @Override
  public String getExportedType() {
    return title.getExportedType();
  }
}
