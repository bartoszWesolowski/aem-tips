package com.aemexampless.aem.listener;

import com.aemexampless.aem.listener.VanityUrlMappings.DuplicatedVanityUrlEntry;
import java.util.List;

public interface DuplicatedVanityUrlErrorHandler {

  void handledDuplicates(List<DuplicatedVanityUrlEntry> duplicated);
}
