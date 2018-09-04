package com.workhelper.user.dao;


import com.workhelper.common.dao.IRepository;
import com.workhelper.user.model.BaseUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends IRepository<BaseUser, String> {
    @Query(" from BaseUser where id = :userId")
    BaseUser getById(@Param("userId") String userId);
}
