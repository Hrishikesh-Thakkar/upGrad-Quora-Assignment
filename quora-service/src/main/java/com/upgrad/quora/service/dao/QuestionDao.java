package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.QuestionEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QuestionDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void createQuestion(QuestionEntity questionEntity) {
        entityManager.persist(questionEntity);
    }

    public List<QuestionEntity> getAllQuestions() {
        return entityManager.createNamedQuery("getAllQuestions", QuestionEntity.class).getResultList();
    }

    public QuestionEntity getQuestionById(String questionId) {
        try {
            return entityManager.createNamedQuery("getQuestionById", QuestionEntity.class).setParameter("uuid", questionId).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public void updateQuestion(QuestionEntity questionEntity) {
        entityManager.merge(questionEntity);
    }

    public void deleteQuestion(QuestionEntity questionEntity) {
        entityManager.remove(questionEntity);
    }

    public List<QuestionEntity> getAllQuestionsById(Integer id) {
        return entityManager.createNamedQuery("getAllQuestionsById", QuestionEntity.class).setParameter("id", id).getResultList();
    }
}
