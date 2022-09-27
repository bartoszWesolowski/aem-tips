mvn -B org.apache.maven.plugins:maven-archetype-plugin:3.2.1:generate \
 -D archetypeGroupId=com.adobe.aem \
 -D archetypeArtifactId=aem-project-archetype \
 -D archetypeVersion=39 \
 -D aemVersion=6.5.9 \
 -D appTitle="AEM Exam examples" \
 -D appId="aem-exam" \
 -D groupId="com.aem.exam" \
 -D frontendModule=general \
 -D includeExamples=y \
 -D includeErrorHandler=y \
 -D singleCountry=n

