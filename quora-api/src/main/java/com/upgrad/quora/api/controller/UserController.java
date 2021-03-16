package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.SigninResponse;
import com.upgrad.quora.api.model.SignupUserRequest;
import com.upgrad.quora.api.model.SignupUserResponse;
import com.upgrad.quora.service.business.UserService;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UsersEntity;
import com.upgrad.quora.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.POST, path="/signup", consumes= MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SignupUserResponse> signup(final SignupUserRequest signupUserRequest) throws SignUpRestrictedException {
        String username = signupUserRequest.getUserName();
        String email = signupUserRequest.getEmailAddress();
        userService.getUserByUsername(username);
        userService.getUserByEmail(email);

        UsersEntity newUsersEntity;
        newUsersEntity = new UsersEntity();
        newUsersEntity.setEmail(email);
        newUsersEntity.setUuid(UUID.randomUUID().toString());
        newUsersEntity.setUsername(username);
        newUsersEntity.setPassword(signupUserRequest.getPassword());
        newUsersEntity.setFirstName(signupUserRequest.getFirstName());
        newUsersEntity.setLastName(signupUserRequest.getFirstName());
        newUsersEntity.setContactNumber(signupUserRequest.getContactNumber());
        newUsersEntity.setAboutMe(signupUserRequest.getAboutMe());
        newUsersEntity.setCountry(signupUserRequest.getCountry());
        newUsersEntity.setDob(signupUserRequest.getDob());
        newUsersEntity.setRole("nonadmin");
        userService.createUser(newUsersEntity);
        SignupUserResponse signupUserResponse = new SignupUserResponse().id(newUsersEntity.getUuid()).status("This user has already been registered, try with any other emailId");
        return new ResponseEntity<SignupUserResponse>(signupUserResponse, HttpStatus.OK);
    }

    @RequestMapping(method= RequestMethod.POST, path="/signin", consumes= MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SigninResponse> signin(@RequestHeader final String authorization) {
        byte[] decode = Base64.getDecoder().decode(authorization.split("Basic ")[1]);
        String decodedText = new String(decode);
        String[] decodedArray = decodedText.split(":");
        String username = decodedArray[0];
        String password = decodedArray[1];
        UserAuthEntity userAuthEntity;
        return new ResponseEntity<SigninResponse>(HttpStatus.OK);
    }

    @RequestMapping(method= RequestMethod.POST, path="/signout", consumes= MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SignupUserResponse> signout(final SignupUserRequest signupUserRequest) {
        return new ResponseEntity<SignupUserResponse>(HttpStatus.OK);
    }


}

