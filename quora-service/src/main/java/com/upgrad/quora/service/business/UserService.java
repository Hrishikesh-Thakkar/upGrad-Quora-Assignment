package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.UsersEntity;
import com.upgrad.quora.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordCryptographyProvider cryptographyProvider;

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
}
