/*
 * Admin API
 * API of Admin Services
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiCallback;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.Configuration;
import org.openapitools.client.Pair;
import org.openapitools.client.ProgressRequestBody;
import org.openapitools.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.openapitools.client.model.UserDeleteResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Api003DeleteUserApi {
    private ApiClient localVarApiClient;

    public Api003DeleteUserApi() {
        this(Configuration.getDefaultApiClient());
    }

    public Api003DeleteUserApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for deleteUser
     * @param userId Unique identifier of User in a standard UUID format (required)
     * @param authorization Mandatory user credentials in bearer http authentication scheme format. (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK- USER SUCCESSFULLY DELETED </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> BAD REQUEST - server cannot or will not process the request due to something that is perceived to be a client error (e.g., malformed request syntax, mandatory elements are missing) </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
        <tr><td> 401 </td><td> UNAUTHORIZED - user has provided wrong credentials </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
        <tr><td> 403 </td><td> FORBIDDEN - request has not been applied because client application has no permission to perform action </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
        <tr><td> 422 </td><td> UNPROCESSABLE ENTITY - request was well-formed but server unable to process due to semantic errors </td><td>  * request-id - Used as correlation id while tracing for the request flow to the backend <br>  </td></tr>
        <tr><td> 500 </td><td> INTERNAL SERVER ERROR - server encountered an unexpected condition that prevented it from fulfilling the request. </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
     </table>
     */
    public okhttp3.Call deleteUserCall(String userId, String authorization, final ApiCallback _callback) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/admin/user/{userId}"
            .replaceAll("\\{" + "userId" + "\\}", localVarApiClient.escapeString(userId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (authorization != null) {
            localVarHeaderParams.put("authorization", localVarApiClient.parameterToString(authorization));
        }

        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[] { "BearerAuthorization" };
        return localVarApiClient.buildCall(localVarPath, "DELETE", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call deleteUserValidateBeforeCall(String userId, String authorization, final ApiCallback _callback) throws ApiException {
        
        // verify the required parameter 'userId' is set
        if (userId == null) {
            throw new ApiException("Missing the required parameter 'userId' when calling deleteUser(Async)");
        }
        
        // verify the required parameter 'authorization' is set
        if (authorization == null) {
            throw new ApiException("Missing the required parameter 'authorization' when calling deleteUser(Async)");
        }
        

        okhttp3.Call localVarCall = deleteUserCall(userId, authorization, _callback);
        return localVarCall;

    }

    /**
     * userDelete
     * Admin can delete a user. 
     * @param userId Unique identifier of User in a standard UUID format (required)
     * @param authorization Mandatory user credentials in bearer http authentication scheme format. (required)
     * @return UserDeleteResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK- USER SUCCESSFULLY DELETED </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> BAD REQUEST - server cannot or will not process the request due to something that is perceived to be a client error (e.g., malformed request syntax, mandatory elements are missing) </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
        <tr><td> 401 </td><td> UNAUTHORIZED - user has provided wrong credentials </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
        <tr><td> 403 </td><td> FORBIDDEN - request has not been applied because client application has no permission to perform action </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
        <tr><td> 422 </td><td> UNPROCESSABLE ENTITY - request was well-formed but server unable to process due to semantic errors </td><td>  * request-id - Used as correlation id while tracing for the request flow to the backend <br>  </td></tr>
        <tr><td> 500 </td><td> INTERNAL SERVER ERROR - server encountered an unexpected condition that prevented it from fulfilling the request. </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
     </table>
     */
    public UserDeleteResponse deleteUser(String userId, String authorization) throws ApiException {
        ApiResponse<UserDeleteResponse> localVarResp = deleteUserWithHttpInfo(userId, authorization);
        return localVarResp.getData();
    }

    /**
     * userDelete
     * Admin can delete a user. 
     * @param userId Unique identifier of User in a standard UUID format (required)
     * @param authorization Mandatory user credentials in bearer http authentication scheme format. (required)
     * @return ApiResponse&lt;UserDeleteResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK- USER SUCCESSFULLY DELETED </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> BAD REQUEST - server cannot or will not process the request due to something that is perceived to be a client error (e.g., malformed request syntax, mandatory elements are missing) </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
        <tr><td> 401 </td><td> UNAUTHORIZED - user has provided wrong credentials </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
        <tr><td> 403 </td><td> FORBIDDEN - request has not been applied because client application has no permission to perform action </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
        <tr><td> 422 </td><td> UNPROCESSABLE ENTITY - request was well-formed but server unable to process due to semantic errors </td><td>  * request-id - Used as correlation id while tracing for the request flow to the backend <br>  </td></tr>
        <tr><td> 500 </td><td> INTERNAL SERVER ERROR - server encountered an unexpected condition that prevented it from fulfilling the request. </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
     </table>
     */
    public ApiResponse<UserDeleteResponse> deleteUserWithHttpInfo(String userId, String authorization) throws ApiException {
        okhttp3.Call localVarCall = deleteUserValidateBeforeCall(userId, authorization, null);
        Type localVarReturnType = new TypeToken<UserDeleteResponse>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * userDelete (asynchronously)
     * Admin can delete a user. 
     * @param userId Unique identifier of User in a standard UUID format (required)
     * @param authorization Mandatory user credentials in bearer http authentication scheme format. (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> OK- USER SUCCESSFULLY DELETED </td><td>  -  </td></tr>
        <tr><td> 400 </td><td> BAD REQUEST - server cannot or will not process the request due to something that is perceived to be a client error (e.g., malformed request syntax, mandatory elements are missing) </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
        <tr><td> 401 </td><td> UNAUTHORIZED - user has provided wrong credentials </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
        <tr><td> 403 </td><td> FORBIDDEN - request has not been applied because client application has no permission to perform action </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
        <tr><td> 422 </td><td> UNPROCESSABLE ENTITY - request was well-formed but server unable to process due to semantic errors </td><td>  * request-id - Used as correlation id while tracing for the request flow to the backend <br>  </td></tr>
        <tr><td> 500 </td><td> INTERNAL SERVER ERROR - server encountered an unexpected condition that prevented it from fulfilling the request. </td><td>  * request-id - Used as correlation id while tracing for the request in the backend <br>  </td></tr>
     </table>
     */
    public okhttp3.Call deleteUserAsync(String userId, String authorization, final ApiCallback<UserDeleteResponse> _callback) throws ApiException {

        okhttp3.Call localVarCall = deleteUserValidateBeforeCall(userId, authorization, _callback);
        Type localVarReturnType = new TypeToken<UserDeleteResponse>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}