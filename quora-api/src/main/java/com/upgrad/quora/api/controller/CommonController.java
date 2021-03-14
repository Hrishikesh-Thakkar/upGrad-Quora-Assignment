package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.UserDetailsResponse;
import com.upgrad.quora.service.exception.AuthenticationFailedException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CommonController {
    @RequestMapping(method= RequestMethod.GET, path="/userprofile/{userId}", consumes= MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDetailsResponse> fetchUserDetails (@PathVariable("userId") final String userId,
                                                      @RequestHeader("authorization") final String authorization) throws AuthenticationFailedException, UserNotFoundException {

        return new ResponseEntity<UserDetailsResponse>(HttpStatus.OK);
    }

}
