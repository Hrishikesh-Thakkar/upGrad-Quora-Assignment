package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UsersEntity;
import com.upgrad.quora.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;

    public UsersEntity getUserById(final String userId) throws UserNotFoundException {
        final UsersEntity usersEntity = userDao.getUserByUUID(userId);
        if(usersEntity == null){
            throw new UserNotFoundException("USR-001","User with entered uuid to be deleted does not exist");
        }
        return usersEntity;
    }

    public UsersEntity getUserByUsername(final String username) throws SignUpRestrictedException {
        final UsersEntity usersEntity = userDao.getUserByUsername(username);
        if(usersEntity == null){
            return null;
        } else {
            throw new SignUpRestrictedException("SGR-001","Try any other Username, this Username has already been taken");
        }
    }

    public UsersEntity getUserByEmail(final String email) throws SignUpRestrictedException{
        final UsersEntity usersEntity = userDao.getUserByEmail(email);
        if(usersEntity == null){
            return null;
        } else {
            throw new SignUpRestrictedException("SGR-002","This user has already been registered, try with any other emailId");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UsersEntity createUser(UsersEntity userEntity){
        String password = userEntity.getPassword();
        if (password == null) {
            userEntity.setPassword("proman@123");
        }
        String[] encryptedText = cryptographyProvider.encrypt(userEntity.getPassword());
        userEntity.setSalt(encryptedText[0]);
        userEntity.setPassword(encryptedText[1]);
        return userDao.createUser(userEntity);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserAuthEntity authenticate(String username, String password) throws AuthenticationFailedException {
        UsersEntity usersEntity = userDao.getUserByUsername(username);
        if(usersEntity == null){
            throw new AuthenticationFailedException("ATH-001","This username does not exist");
        }

        final String encryptedPassword = cryptographyProvider.encrypt(password,usersEntity.getSalt());

        if(encryptedPassword.contentEquals(usersEntity.getPassword())){
            JwtTokenProvider jwtTokenProvider = new JwtTokenProvider(encryptedPassword);
            UserAuthEntity userAuthEntity = new UserAuthEntity();
            userAuthEntity.setUsersEntity(usersEntity);
            final ZonedDateTime now = ZonedDateTime.now();
            final ZonedDateTime expiresAt = now.plusHours(8);
            userAuthEntity.setLoginAt(now);
            userAuthEntity.setExpiresAt(expiresAt);
            userAuthEntity.setUuid(usersEntity.getUuid());
            userAuthEntity.setAccessToken(jwtTokenProvider.generateToken(usersEntity.getUuid(),now,expiresAt));
            userDao.createUserAuth(userAuthEntity);
            return userAuthEntity;
        }
        throw new AuthenticationFailedException("ATH-002","Password failed");
    }

    public UserAuthEntity getUserAuthByToken(String authorization) throws SignOutRestrictedException {
        UserAuthEntity userAuthEntity = userDao.getUserAuthEntity(authorization);
        if(userAuthEntity == null){
            throw new SignOutRestrictedException("SGR-001","User is not Signed in");
        }
        return userAuthEntity;
    }

    public UserAuthEntity updateUserAuthEntity(UserAuthEntity userAuthEntity){
        userAuthEntity.setLogoutAt(ZonedDateTime.now());
        userDao.updateUserAuthEntity(userAuthEntity);
        return userAuthEntity;
    }
}
