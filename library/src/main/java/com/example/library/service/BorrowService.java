package com.example.library.service;

import com.example.library.exception.borrowNotAvailable;
import com.example.library.mapper.BorrowMapper;
import com.example.library.model.Borrow;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowService {
    @Autowired
    private SqlSessionTemplate sqlSession;


    public int addBorrow(Borrow borrow) {
        BorrowMapper borrowMapper = sqlSession.getMapper(BorrowMapper.class);
        return borrowMapper.insert(borrow);
    }

    public List<Borrow> viewBorrow(Long userId) {
        BorrowMapper borrowMapper = sqlSession.getMapper(BorrowMapper.class);
        return borrowMapper.selectAll(userId);
    }

    public int delBorrow(Long borrowId) {
        BorrowMapper borrowMapper = sqlSession.getMapper(BorrowMapper.class);
        int deletedRows = borrowMapper.deleteByPrimaryKey(borrowId);
        if(deletedRows == 0)
            throw new borrowNotAvailable(borrowId);
        return deletedRows;
    }
}
