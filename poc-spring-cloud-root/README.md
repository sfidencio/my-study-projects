# POC spring cloud

### Módulos
- gateway
- product-service
  - database
    - pgsql 
- customer-service
  - database
    - mongo

### Build na raiz
```shell
mvn clean install
```

### Execução
- gateway
```shell
cd gateway
mvn spring-boot:run
```

- product-service
```shell
cd product-service
mvn spring-boot:run
```

- customer-service
```shell
cd customer-service
mvn spring-boot:run
```

### Executando via `mvn` a partir da raiz
```shell
mvn -pl gateway spring-boot:run
mvn -pl product-service spring-boot:run
mvn -pl customer-service spring-boot:run
```
### Testando se o eureka está funcionando via curl
```shell
curl http://localhost:8761/eureka/apps
```
- Retornaria algo do tipo
```xml 
<applications>
  <versions__delta>1</versions__delta>
  <apps__hashcode>UP_3_</apps__hashcode>
  <application>
    <name>GATEWAY</name>
    <instance>
      <instanceId>192.168.1.105:gateway</instanceId>
      <hostName>192.168.1.105</hostName>
      <app>GATEWAY</app>
      <ipAddr>192.168.1.105</ipAddr>
      <status>UP</status>
      <overriddenstatus>UNKNOWN</overriddenstatus>
      <port enabled="true">8080</port>
      <securePort enabled="false">443</securePort>
      <countryId>1</countryId>
      <dataCenterInfo class="com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo">
        <name>MyOwn</name>
      </dataCenterInfo>
      <leaseInfo>
        <renewalIntervalInSecs>30</renewalIntervalInSecs>
        <durationInSecs>90</durationInSecs>
        <registrationTimestamp>1724180586376</registrationTimestamp>
        <lastRenewalTimestamp>1724180766495</lastRenewalTimestamp>
        <evictionTimestamp>0</evictionTimestamp>
        <serviceUpTimestamp>1724180585850</serviceUpTimestamp>
      </leaseInfo>
      <metadata>
        <management.port>8080</management.port>
      </metadata>
      <homePageUrl>http://192.168.1.105:8080/</homePageUrl>
      <statusPageUrl>http://192.168.1.105:8080/actuator/info</statusPageUrl>
      <healthCheckUrl>http://192.168.1.105:8080/actuator/health</healthCheckUrl>
      <vipAddress>gateway</vipAddress>
      <secureVipAddress>gateway</secureVipAddress>
      <isCoordinatingDiscoveryServer>false</isCoordinatingDiscoveryServer>
      <lastUpdatedTimestamp>1724180586376</lastUpdatedTimestamp>
      <lastDirtyTimestamp>1724180585834</lastDirtyTimestamp>
      <actionType>ADDED</actionType>
    </instance>
  </application>
  <application>
    <name>CUSTOMER-SERVICE</name>
    <instance>
      <instanceId>192.168.1.105:customer-service:8082</instanceId>
      <hostName>192.168.1.105</hostName>
      <app>CUSTOMER-SERVICE</app>
      <ipAddr>192.168.1.105</ipAddr>
      <status>UP</status>
      <overriddenstatus>UNKNOWN</overriddenstatus>
      <port enabled="true">8082</port>
      <securePort enabled="false">443</securePort>
      <countryId>1</countryId>
      <dataCenterInfo class="com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo">
        <name>MyOwn</name>
      </dataCenterInfo>
      <leaseInfo>
        <renewalIntervalInSecs>30</renewalIntervalInSecs>
        <durationInSecs>90</durationInSecs>
        <registrationTimestamp>1724180671053</registrationTimestamp>
        <lastRenewalTimestamp>1724180791073</lastRenewalTimestamp>
        <evictionTimestamp>0</evictionTimestamp>
        <serviceUpTimestamp>1724180670534</serviceUpTimestamp>
      </leaseInfo>
      <metadata>
        <management.port>8082</management.port>
      </metadata>
      <homePageUrl>http://192.168.1.105:8082/</homePageUrl>
      <statusPageUrl>http://192.168.1.105:8082/actuator/info</statusPageUrl>
      <healthCheckUrl>http://192.168.1.105:8082/actuator/health</healthCheckUrl>
      <vipAddress>customer-service</vipAddress>
      <secureVipAddress>customer-service</secureVipAddress>
      <isCoordinatingDiscoveryServer>false</isCoordinatingDiscoveryServer>
      <lastUpdatedTimestamp>1724180671053</lastUpdatedTimestamp>
      <lastDirtyTimestamp>1724180670507</lastDirtyTimestamp>
      <actionType>ADDED</actionType>
    </instance>
  </application>
  <application>
    <name>PRODUCT-SERVICE</name>
    <instance>
      <instanceId>192.168.1.105:product-service:8081</instanceId>
      <hostName>192.168.1.105</hostName>
      <app>PRODUCT-SERVICE</app>
      <ipAddr>192.168.1.105</ipAddr>
      <status>UP</status>
      <overriddenstatus>UNKNOWN</overriddenstatus>
      <port enabled="true">8081</port>
      <securePort enabled="false">443</securePort>
      <countryId>1</countryId>
      <dataCenterInfo class="com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo">
        <name>MyOwn</name>
      </dataCenterInfo>
      <leaseInfo>
        <renewalIntervalInSecs>30</renewalIntervalInSecs>
        <durationInSecs>90</durationInSecs>
        <registrationTimestamp>1724180650671</registrationTimestamp>
        <lastRenewalTimestamp>1724180770653</lastRenewalTimestamp>
        <evictionTimestamp>0</evictionTimestamp>
        <serviceUpTimestamp>1724180650161</serviceUpTimestamp>
      </leaseInfo>
      <metadata>
        <management.port>8081</management.port>
      </metadata>
      <homePageUrl>http://192.168.1.105:8081/</homePageUrl>
      <statusPageUrl>http://192.168.1.105:8081/actuator/info</statusPageUrl>
      <healthCheckUrl>http://192.168.1.105:8081/actuator/health</healthCheckUrl>
      <vipAddress>product-service</vipAddress>
      <secureVipAddress>product-service</secureVipAddress>
      <isCoordinatingDiscoveryServer>false</isCoordinatingDiscoveryServer>
      <lastUpdatedTimestamp>1724180650671</lastUpdatedTimestamp>
      <lastDirtyTimestamp>1724180650125</lastDirtyTimestamp>
      <actionType>ADDED</actionType>
    </instance>
  </application>
</applications>     
```
### checando se o service discovery está funcionando
```shell
netstat -an | grep 8761
```
- Retornaria algo do tipo
```shell
tcp6       0      0 :::8761                 :::*                    LISTEN
```

