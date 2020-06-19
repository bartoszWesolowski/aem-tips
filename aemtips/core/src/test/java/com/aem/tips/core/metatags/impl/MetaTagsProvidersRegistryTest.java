package com.aem.tips.core.metatags.impl;

import static com.aem.tips.core.metatags.impl.MockedMetaTag.invalid;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.aem.tips.core.metatags.api.MetaTag;
import com.aem.tips.core.metatags.api.MetaTagsProvider;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AemContextExtension.class)
class MetaTagsProvidersRegistryTest {

  private AemContext context;

  private MetaTagsProvidersRegistry tested = new MetaTagsProvidersRegistry();

  @BeforeEach
  void setUp() {
    context = new AemContext();
  }

  @Test
  @DisplayName("Should return empty list when there are no providers registered.")
  void shouldInjectProviders() {
    givenTestedServiceRegisteredWithDependencies();

    final List<MetaTag> actual = tested.createMetaTags(context.request());

    assertThat(actual).isEmpty();
  }

  @Test
  @DisplayName("Given many providers registered should return all created valid meta tags")
  void shouldGetValidMetaTagsFromAllProviders() {
    final MetaTag firstValid = MockedMetaTag.valid();
    final MetaTag secondValid = MockedMetaTag.valid();
    givenProviderThatReturnsListContainingMetaTags(firstValid, invalid());
    givenProviderThatReturnsListContainingMetaTags(invalid(), secondValid);
    givenTestedServiceRegisteredWithDependencies();

    final List<MetaTag> actual = tested.createMetaTags(context.request());

    assertThat(actual).containsExactlyInAnyOrder(firstValid, secondValid);
  }

  @Test
  @DisplayName("Given many providers registered should return all created valid meta tags")
  void providerThrowingExceptionShouldBeIgnored() {
    final MetaTag firstValid = MockedMetaTag.valid();
    final MetaTag secondValid = MockedMetaTag.valid();
    givenProviderThatReturnsListContainingMetaTags(invalid(), firstValid, secondValid, invalid());
    givenProviderThatThrowsRuntimeException();
    givenTestedServiceRegisteredWithDependencies();

    final List<MetaTag> actual = tested.createMetaTags(context.request());

    assertThat(actual).containsExactlyInAnyOrder(firstValid, secondValid);
  }


  private void givenTestedServiceRegisteredWithDependencies() {
    context.registerInjectActivateService(tested);
  }

  private void givenProviderThatReturnsListContainingMetaTags(MetaTag... metaTags) {
    context.registerService(MetaTagsProvider.class, new MockedMetaTagProvider(Arrays.asList(metaTags)));
  }

  private void givenProviderThatThrowsRuntimeException() {
    context.registerService(MetaTagsProvider.class, new MockedMetaTagProviderThrowingException());
  }
}