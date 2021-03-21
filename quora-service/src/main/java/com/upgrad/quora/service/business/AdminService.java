package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UsersEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    @Autowired
    private UserDao userDao;

    //Validate admin access by first fetching the UserAuthEntity from the accessToken
    //Check if it is valid and not logged out
    //Then check the user entity and see that the users entity is admin or nonadmin
    public UsersEntity validateAdminAccess(String accessToken) throws AuthorizationFailedException {
        UserAuthEntity userAuthEntity = userDao.getUserAuthEntity(accessToken);
        if (userAuthEntity == null) {
            throw new AuthorizationFailedException("ATHR-001", "User has not signed in");
        }

        if (userAuthEntity.getLogoutAt() != null) {
            throw new AuthorizationFailedException("ATHR-002", "User is signed out");
        }

        if (userAuthEntity.getUsersEntity().getRole().contentEquals("nonadmin")) {
            throw new AuthorizationFailedException("ATHR-003", "Unauthorized Access, Entered user is not an admin");
        }

        return userAuthEntity.getUsersEntity();
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(UsersEntity targetUser) {
        userDao.deleteUsersEntity(targetUser);
    }
}
