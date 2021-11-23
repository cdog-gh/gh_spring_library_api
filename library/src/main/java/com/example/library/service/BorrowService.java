package com.example.library.service;

import com.example.library.exception.bookNotAvailable;
import com.example.library.exception.returnBookNotAvailable;
import com.example.library.mapper.db.BorrowMapper;
import com.example.library.model.Borrow.Borrow;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BorrowService {
    @Autowired
    private SqlSessionTemplate sqlSession;

    /*
    (1) 책이 없는 경우에는 0개의 row 가 추가되고
    (2) 이미 빌리려는 책을 누군가 빌린 경우에는 예외가 떨어진다.
     */
    public int addBorrow(Borrow borrow) {
        BorrowMapper borrowMapper = sqlSession.getMapper(BorrowMapper.class);
        try{
            int insertedRows = borrowMapper.insert(borrow);
            if(insertedRows == 0)
                throw new bookNotAvailable(borrow.getBookId());
            return insertedRows;
        }catch(DuplicateKeyException e){
            throw new bookNotAvailable(borrow.getBookId());
        }
    }

    public List<Borrow> viewBorrow(Long userId) {
        BorrowMapper borrowMapper = sqlSession.getMapper(BorrowMapper.class);
        return borrowMapper.selectAll(userId);
    }

    public int delBorrow(Long borrowId) {
        BorrowMapper borrowMapper = sqlSession.getMapper(BorrowMapper.class);
        int deletedRows = borrowMapper.deleteByPrimaryKey(borrowId);
        if(deletedRows == 0)
            throw new returnBookNotAvailable(borrowId);
        return deletedRows;
    }
}
