package nz.co.xingsoft.mybatis.service;

import javax.inject.Inject;

import nz.co.xingsoft.mybatis.persistence.domain.User;
import nz.co.xingsoft.mybatis.persistence.mapper.UserMapper;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Inject
    private UserMapper mapper;

    public User getUserByUsername(final String username) {
        return mapper.getUserByUsername(username);
    }

}
