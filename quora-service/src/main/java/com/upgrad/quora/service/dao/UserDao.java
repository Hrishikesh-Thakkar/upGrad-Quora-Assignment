package com.upgrad.quora.service.dao;

import com.upgrad.quora.service.entity.AnswerEntity;
import com.upgrad.quora.service.entity.QuestionEntity;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UsersEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public UsersEntity getUserByUsername(final String username) {
        try {
            return entityManager.createNamedQuery("userByUsername", UsersEntity.class).setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public UsersEntity getUserByEmail(final String email) {
        try {
            return entityManager.createNamedQuery("userByEmail", UsersEntity.class).setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public UsersEntity getUserByUUID(final String userUuid) {
        try {
            return entityManager.createNamedQuery("userByUuid", UsersEntity.class).setParameter("uuid", userUuid)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public UsersEntity createUser(UsersEntity newUsersEntity) {
        entityManager.persist(newUsersEntity);
        return newUsersEntity;
    }

    public UserAuthEntity createUserAuth(UserAuthEntity userAuthEntity) {
        entityManager.persist(userAuthEntity);
        return userAuthEntity;
    }

    public UserAuthEntity getUserAuthEntity(String accessToken) {
        try {
            return entityManager.createNamedQuery("userAuthTokenByAccessToken", UserAuthEntity.class).
                    setParameter("accessToken", accessToken).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public UserAuthEntity updateUserAuthEntity(UserAuthEntity userAuthEntity) {
        entityManager.merge(userAuthEntity);
        return userAuthEntity;
    }

    public void deleteUsersEntity(UsersEntity targetUser) {
        for(QuestionEntity questionEntity : targetUser.getQuestionEntities()){
            entityManager.remove(questionEntity);
        }

        for(AnswerEntity answerEntity : targetUser.getAnswerEntities()){
            entityManager.remove(answerEntity);
        }
        try {
            entityManager.createQuery("delete from UsersEntity u where u.uuid = :uuid").setParameter("uuid",targetUser.getUuid()).executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
