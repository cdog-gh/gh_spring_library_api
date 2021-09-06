package com.example.library.mapper;

import com.example.library.model.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);
    int insert(User record);
    User selectByName(String userName);
    List<User> selectAll();
    int updateByPrimaryKey(User record);
}