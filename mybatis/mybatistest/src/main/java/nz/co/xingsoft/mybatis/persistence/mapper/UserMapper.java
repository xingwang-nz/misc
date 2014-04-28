package nz.co.xingsoft.mybatis.persistence.mapper;

import nz.co.xingsoft.mybatis.persistence.domain.User;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    public User getUserByUsername(@Param("username") String username);
}
