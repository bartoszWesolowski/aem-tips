mvn -B archetype:generate \
 -D archetypeGroupId=com.adobe.granite.archetypes \
 -D archetypeArtifactId=aem-project-archetype \
 -D archetypeVersion=23 \
 -D aemVersion=6.5.0 \
 -D appTitle="AEM Tips" \
 -D appId="aemtips" \
 -D groupId="com.aem.tips" \
 -D frontendModule=general \
 -D includeExamples=n \
 -D singleCountry=n

