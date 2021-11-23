package com.example.library.service;

import com.example.library.exception.usernameDuplicate;
import com.example.library.mapper.db.UserMapper;
import com.example.library.model.User.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private SqlSessionTemplate sqlSession;

    public User getUser(User user) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.selectByName(user.getUserName());
    }

    public int regUser(User user) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        try{
            return userMapper.insert(user);
        }catch(DuplicateKeyException e){
            throw new usernameDuplicate("userName", user.getUserName());
        }
    }
}
