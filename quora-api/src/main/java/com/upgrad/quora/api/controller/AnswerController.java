package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.*;
import com.upgrad.quora.service.business.AnswerService;
import com.upgrad.quora.service.business.QuestionService;
import com.upgrad.quora.service.entity.AnswerEntity;
import com.upgrad.quora.service.entity.QuestionEntity;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.exception.AnswerNotFoundException;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.InvalidQuestionException;
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
@RequestMapping("/")
public class AnswerController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @RequestMapping(method = RequestMethod.POST, path = "/question/{questionId}/answer/create", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AnswerResponse> createAnswer(@PathVariable("questionId") String questionId, @RequestHeader String authorization, final AnswerRequest answerRequest) throws AuthorizationFailedException, InvalidQuestionException {
        UserAuthEntity userAuthEntity = questionService.getUserAuthByToken(authorization);
        QuestionEntity questionEntity = questionService.getQuestion(questionId);
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setAns(answerRequest.getAnswer());
        answerEntity.setUsersEntity(userAuthEntity.getUsersEntity());
        answerEntity.setDate(ZonedDateTime.now());
        answerEntity.setUuid(UUID.randomUUID().toString());
        answerEntity.setQuestionEntity(questionEntity);
        answerService.createAnswer(answerEntity);
        AnswerResponse answerResponse = new AnswerResponse().id(answerEntity.getUuid()).status("ANSWER CREATED");
        return new ResponseEntity<>(answerResponse, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/answer/edit/{answerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AnswerEditResponse> editAnswer(@PathVariable("answerId") String answerId, @RequestHeader final String authorization, final AnswerEditRequest answerEditRequest) throws AuthorizationFailedException, AnswerNotFoundException {
        UserAuthEntity userAuthEntity = questionService.getUserAuthByToken(authorization);
        AnswerEntity answerEntity = answerService.getAnswerById(answerId, userAuthEntity.getUsersEntity());
        answerEntity.setAns(answerEditRequest.getContent());
        answerService.updateAnswer(answerEntity);
        AnswerEditResponse answerEditResponse = new AnswerEditResponse().id(answerEntity.getUuid()).status("ANSWER EDITED");
        return new ResponseEntity<AnswerEditResponse>(answerEditResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/answer/delete/{answerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<AnswerDeleteResponse> deleteAnswer(@PathVariable("answerId") String answerId, @RequestHeader final String authorization) throws AuthorizationFailedException, AnswerNotFoundException {
        UserAuthEntity userAuthEntity = questionService.getUserAuthByToken(authorization);
        AnswerEntity answerEntity = answerService.getAnswerById(answerId, userAuthEntity.getUsersEntity());
        answerService.deleteAnswer(answerEntity);
        AnswerDeleteResponse answerDeleteResponse = new AnswerDeleteResponse().id(answerEntity.getUuid()).status("ANSWER DELETED");
        return new ResponseEntity<AnswerDeleteResponse>(answerDeleteResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/answer/all/{questionId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<AnswerResponse>> getAnswersForQuestion(@PathVariable("questionId") String questionId, @RequestHeader final String authorization) throws AuthorizationFailedException, InvalidQuestionException {
        UserAuthEntity userAuthEntity = questionService.getUserAuthByToken(authorization);
        QuestionEntity questionEntity = questionService.getQuestion(questionId);
        List<AnswerEntity> answerEntityList = answerService.getAnswersForQuestion(questionEntity);
        List<AnswerResponse> answerResponses = answerEntityList.stream()
                .map(a -> new AnswerResponse().id(a.getUuid()).status(a.getAns()))
                .collect(Collectors.toList());
        return new ResponseEntity<List<AnswerResponse>>(answerResponses, HttpStatus.OK);
    }
}
