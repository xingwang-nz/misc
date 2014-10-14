package nz.co.xingsoft.jsf.persistence.dao;

import nz.co.xingsoft.jsf.common.QueryParameter;
import nz.co.xingsoft.jsf.persistence.entity.User;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserDao
        extends AbstractDao<User> {

    @Transactional(readOnly = true)
    public User getUserByUserName(final String username) {
        return commonDao.getSingleResultByQuery("select u from User u left join fetch u.roles where upper(u.username)=:username", new QueryParameter(
                "username", username.toUpperCase()));
    }

}
