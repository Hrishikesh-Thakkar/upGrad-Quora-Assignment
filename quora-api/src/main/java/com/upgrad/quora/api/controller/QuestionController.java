package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.*;
import com.upgrad.quora.service.business.QuestionService;
import com.upgrad.quora.service.business.UserService;
import com.upgrad.quora.service.entity.QuestionEntity;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UsersEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.InvalidQuestionException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, path = "/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionResponse> createQuestion(@RequestHeader final String authorization, final QuestionRequest questionRequest) throws AuthorizationFailedException {
        UserAuthEntity userAuthEntity = questionService.getUserAuthByToken(authorization);
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setUsersEntity(userAuthEntity.getUsersEntity());
        questionEntity.setDate(ZonedDateTime.now());
        questionEntity.setContent(questionRequest.getContent());
        questionEntity.setUuid(UUID.randomUUID().toString());
        questionService.createQuestion(questionEntity);
        QuestionResponse questionResponse = new QuestionResponse().id(questionEntity.getUuid()).status("QUESTION CREATED");
        return new ResponseEntity<QuestionResponse>(questionResponse, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<QuestionResponse>> getAllQuestions(@RequestHeader final String authorization) throws AuthorizationFailedException {
        List<QuestionEntity> questionEntities = questionService.getAllQuestions(authorization);
        List<QuestionResponse> questionResponses = questionEntities.stream()
                .map(p -> new QuestionResponse().id(p.getUuid()).status(p.getContent()))
                .collect(Collectors.toList());
        return new ResponseEntity<List<QuestionResponse>>(questionResponses, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/edit/{questionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionEditResponse> editQuestion(@RequestHeader final String authorization,
                                                             final QuestionEditRequest questionEditRequest,
                                                             @PathVariable("questionId") String questionId) throws AuthorizationFailedException, InvalidQuestionException {
        UserAuthEntity userAuthByToken = questionService.getUserAuthByToken(authorization);
        QuestionEntity questionEntity = questionService.getQuestion(questionId);
        questionEntity.setContent(questionEditRequest.getContent());
        questionService.updateQuestion(userAuthByToken.getUsersEntity(), questionEntity);
        QuestionEditResponse questionResponse = new QuestionEditResponse().id(questionEntity.getUuid()).status("QUESTION EDITED");
        return new ResponseEntity<QuestionEditResponse>(questionResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{questionId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionDeleteResponse> deleteQuestion(@RequestHeader final String authorization,
                                                                 @PathVariable("questionId") String questionId) throws AuthorizationFailedException, InvalidQuestionException {
        UserAuthEntity userAuthEntity = questionService.getUserAuthByToken(authorization);
        QuestionEntity questionEntity = questionService.getQuestion(questionId);
        questionService.deleteQuestion(userAuthEntity, questionEntity);
        QuestionDeleteResponse questionDeleteResponse = new QuestionDeleteResponse().id(questionId).status("QUESTION DELETED");
        return new ResponseEntity<QuestionDeleteResponse>(questionDeleteResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/all/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<QuestionResponse>> getAllQuestionsById(@RequestHeader final String authorization, @PathVariable("userId") final String userId) throws AuthorizationFailedException, UserNotFoundException {
        UsersEntity usersEntity = userService.getUserById(userId);
        List<QuestionEntity> questionEntities = questionService.getAllQuestionsById(authorization);
        List<QuestionResponse> questionResponses = questionEntities.stream()
                .map(p -> new QuestionResponse().id(p.getUuid()).status(p.getContent()))
                .collect(Collectors.toList());
        return new ResponseEntity<List<QuestionResponse>>(questionResponses, HttpStatus.OK);
    }
}






















