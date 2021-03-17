package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UsersEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UserDao userDao;

    public UsersEntity validateAdminAccess(String accessToken) throws AuthorizationFailedException {
        UserAuthEntity userAuthEntity = userDao.getUserAuthEntity(accessToken);
        if(userAuthEntity == null){
            throw new AuthorizationFailedException("ATHR-001","User has not signed in");
        }

        if(userAuthEntity.getLogoutAt() != null){
            throw new AuthorizationFailedException("ATHR-002","User is signed out");
        }

        if(userAuthEntity.getUsersEntity().getRole().contentEquals("nonadmin")){
            throw new AuthorizationFailedException("ATHR-003","Unauthorized Access, Entered user is not an admin");
        }

        return userAuthEntity.getUsersEntity();
    }

    public void deleteUser(UsersEntity targetUser) {
        userDao.deleteUsersEntity(targetUser);
    }
}
