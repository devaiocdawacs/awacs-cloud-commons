#### sample pubdub cloudEvent awacs cloud generates

```
2020-11-06 18:12:14.555  INFO 16472 --- [bsub-publisher1] c.a.s.p.config.GooglePubSubConfig        : Message was sent successfully.
2020-11-06 18:12:14.564  INFO 16472 --- [sub-subscriber3] c.a.s.p.config.GooglePubSubConfig        : Message arrived! Payload: {
  "eventId": "bdf441c4-049e-4fc8-8bcd-3ef5986dfcdc",
  "eventName": "awacs.cloud.product.order.get",
  "eventSource": "product-service",
  "eventType": "order:3",
  "sessionId": "1D418007417AB43E76F7B6579E61A745",
  "authToken": "[Bearer 0c9880c7-c94a-4e88-8d51-331e6d9995ce]",
  "userId": "a",
  "sessionCreatedAt": "1604665645640",
  "eventCreatedAt": "2020-11-06T18:12:14.2937135"
}

```


#### How to use java module

1. add settings.xml to your ~/.m2/settings.xml

```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"                                                                                                                      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"                                                                                                                       xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0                                                                                                                                      http://maven.apache.org/xsd/settings-1.0.0.xsd">                                                                                                                                                                                                                                                                                    <activeProfiles>                                                                                                                                                              <activeProfile>github</activeProfile>                                                                                                                                     </activeProfiles>                                                                                                                                                                                                                                                                                                                                       <profiles>                                                                                                                                                                    <profile>                                                                                                                                                                     <id>github</id>                                                                                                                                                             <repositories>                                                                                                                                                                <repository>                                                                                                                                                                  <id>central</id>                                                                                                                                                            <url>https://repo1.maven.org/maven2</url>                                                                                                                                   <releases><enabled>true</enabled></releases>                                                                                                                                <snapshots><enabled>true</enabled></snapshots>                                                                                                                            </repository>                                                                                                                                                               <repository>                                                                                                                                                                  <id>github</id>                                                                                                                                                             <name>GitHub Maven Packages</name>                                                                                                                                          <url>https://maven.pkg.github.com/girishaiocdawacs</url>                                                                                                                  </repository>                                                                                                                                                                                                                                                                                                                                                    <repository>                                                                                                                                                         <id>github</id>                                                                                                                                                             <name>GitHub Maven Packages</name>                                                                                                                                          <url>https://maven.pkg.github.com/girishaiocdawacs/smart-pharmacy-event-management</url>                                                                                  </repository>                                                                                                                                                             </repositories>                                                                                                                                                           </profile>                                                                                                                                                                </profiles>                                                                                                                                                                                                                                                                                                                                             <servers>                                                                                                                                                                     <server>                                                                                                                                                                      <id>github</id>                                                                                                                                                             <username>${env.GITHUB_USERNAME}</username>
      <password>${env.GITHUB_TOKEN}</password>
    </server>
  </servers>
</settings>
```

2. Follow steps https://github.com/girishaiocdawacs/smart-pharmacy-event-management/packages/502802

3. Add dependency to your pom

```
<dependency>
  <groupId>com.aiocdawacs</groupId>
  <artifactId>cloud-event-publisher-gcp-pubsub</artifactId>
  <version>1.0.12</version>
</dependency>
```
