package nz.co.xingsoft.jsf.service;

import javax.inject.Inject;

import nz.co.xingsoft.jsf.persistence.dao.UserDao;
import nz.co.xingsoft.jsf.persistence.entity.User;

import org.springframework.stereotype.Component;

@Component
public class MessageService {

    @Inject
    private UserDao userDao;

    public User getMessage() {
        return userDao.getUserByUserName("xing");
    }
}
