package nz.co.xingsoft.mybatis.service;

import static org.fest.assertions.Assertions.assertThat;

import javax.inject.Inject;

import nz.co.xingsoft.mybatis.SpringApplicationContextTest;
import nz.co.xingsoft.mybatis.persistence.domain.User;

import org.junit.Test;

public class UserServiceTest
        extends SpringApplicationContextTest {

    @Inject
    private UserService userService;

    @Test
    public void testGetUserByName() {
        final User user = userService.getUserByUsername("xing");

        assertThat(user).isNotNull();

    }

}
