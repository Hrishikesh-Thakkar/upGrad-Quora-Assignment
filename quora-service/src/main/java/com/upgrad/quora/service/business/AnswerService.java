package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.AnswerDao;
import com.upgrad.quora.service.entity.AnswerEntity;
import com.upgrad.quora.service.entity.QuestionEntity;
import com.upgrad.quora.service.entity.UsersEntity;
import com.upgrad.quora.service.exception.AnswerNotFoundException;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerDao answerDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public void createAnswer(AnswerEntity answerEntity) {
        answerDao.createAnswer(answerEntity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateAnswer(AnswerEntity answerEntity) {
        answerDao.editAnswer(answerEntity);
    }

    public AnswerEntity getAnswerById(String answerId, UsersEntity usersEntity) throws AnswerNotFoundException, AuthorizationFailedException {
        AnswerEntity answerEntity = answerDao.getAnswerById(answerId);
        if (answerEntity == null) {
            throw new AnswerNotFoundException("ANS-001", "Entered answer uuid does not exist");
        }
        if (!answerEntity.getUsersEntity().getId().equals(usersEntity.getId())) {
            throw new AuthorizationFailedException("ATHR-003", "Only the answer owner can edit the answer");
        }
        return answerEntity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAnswer(AnswerEntity answerEntity) {
        answerDao.deleteAnswer(answerEntity);
    }

    public List<AnswerEntity> getAnswersForQuestion(QuestionEntity questionEntity) {
        return answerDao.getAnswersForQuestion(questionEntity);
    }
}
