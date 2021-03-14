# Api003DeleteUserApi

All URIs are relative to *http://quora.io/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**deleteUser**](Api003DeleteUserApi.md#deleteUser) | **DELETE** /admin/user/{userId} | userDelete


<a name="deleteUser"></a>
# **deleteUser**
> UserDeleteResponse deleteUser(userId, authorization)

userDelete

Admin can delete a user. 

### Example
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

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **String**| Unique identifier of User in a standard UUID format |
 **authorization** | **String**| Mandatory user credentials in bearer http authentication scheme format. |

### Return type

[**UserDeleteResponse**](UserDeleteResponse.md)

### Authorization

[BearerAuthorization](../README.md#BearerAuthorization)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK- USER SUCCESSFULLY DELETED |  -  |
**400** | BAD REQUEST - server cannot or will not process the request due to something that is perceived to be a client error (e.g., malformed request syntax, mandatory elements are missing) |  * request-id - Used as correlation id while tracing for the request in the backend <br>  |
**401** | UNAUTHORIZED - user has provided wrong credentials |  * request-id - Used as correlation id while tracing for the request in the backend <br>  |
**403** | FORBIDDEN - request has not been applied because client application has no permission to perform action |  * request-id - Used as correlation id while tracing for the request in the backend <br>  |
**422** | UNPROCESSABLE ENTITY - request was well-formed but server unable to process due to semantic errors |  * request-id - Used as correlation id while tracing for the request flow to the backend <br>  |
**500** | INTERNAL SERVER ERROR - server encountered an unexpected condition that prevented it from fulfilling the request. |  * request-id - Used as correlation id while tracing for the request in the backend <br>  |

