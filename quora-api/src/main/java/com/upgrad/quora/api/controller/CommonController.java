package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.UserDetailsResponse;
import com.upgrad.quora.service.business.CommonService;
import com.upgrad.quora.service.entity.UsersEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @RequestMapping(method= RequestMethod.GET, path="/userprofile/{userId}", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDetailsResponse> fetchUserDetails (@PathVariable("userId") final String userId,
                                                      @RequestHeader("authorization") final String authorization) throws AuthorizationFailedException, UserNotFoundException {

        final UsersEntity usersEntity = commonService.getUser(userId,authorization);
        UserDetailsResponse userDetailsResponse = new UserDetailsResponse().firstName(usersEntity.getFirstName()).
                lastName(usersEntity.getLastName()).userName(usersEntity.getUsername()).dob(usersEntity.getDob()).
                aboutMe(usersEntity.getAboutMe()).contactNumber(usersEntity.getContactNumber()).country(usersEntity.getCountry()).
                emailAddress(usersEntity.getEmail());
        return new ResponseEntity<UserDetailsResponse>(userDetailsResponse,HttpStatus.OK);
    }

}
