# openapi-java-client

Admin API
- API version: 1.0.0
  - Build date: 2021-03-07T13:08:35.674227800+05:30[Asia/Calcutta]

API of Admin Services


*Automatically generated by the [OpenAPI Generator](https://openapi-generator.tech)*


## Requirements

Building the API client library requires:
1. Java 1.7+
2. Maven/Gradle

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn clean install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn clean deploy
```

Refer to the [OSSRH Guide](http://central.sonatype.org/pages/ossrh-guide.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>org.openapitools</groupId>
  <artifactId>openapi-java-client</artifactId>
  <version>1.0.0</version>
  <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "org.openapitools:openapi-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

```shell
mvn clean package
```

Then manually install the following JARs:

* `target/openapi-java-client-1.0.0.jar`
* `target/lib/*.jar`

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.Api003DeleteUserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://quora.io/api/v1");
    
    // Configure HTTP basic authorization: BearerAuthorization
    HttpBasicAuth BearerAuthorization = (HttpBasicAuth) defaultClient.getAuthentication("BearerAuthorization");
    BearerAuthorization.setUsername("YOUR USERNAME");
    BearerAuthorization.setPassword("YOUR PASSWORD");

    Api003DeleteUserApi apiInstance = new Api003DeleteUserApi(defaultClient);
    String userId = "userId_example"; // String | Unique identifier of User in a standard UUID format
    String authorization = "authorization_example"; // String | Mandatory user credentials in bearer http authentication scheme format.
    try {
      UserDeleteResponse result = apiInstance.deleteUser(userId, authorization);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling Api003DeleteUserApi#deleteUser");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://quora.io/api/v1*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*Api003DeleteUserApi* | [**deleteUser**](docs/Api003DeleteUserApi.md#deleteUser) | **DELETE** /admin/user/{userId} | userDelete


## Documentation for Models

 - [UserDeleteResponse](docs/UserDeleteResponse.md)


## Documentation for Authorization

Authentication schemes defined for the API:
### BearerAuthorization

- **Type**: HTTP basic authentication


## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author



