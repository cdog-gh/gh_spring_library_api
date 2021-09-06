package com.example.library.mapper;

import com.example.library.model.Borrow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BorrowMapper {
    int deleteByPrimaryKey(Long borrowId);
    int insert(Borrow record);
    Borrow selectByPrimaryKey(Long borrowId);
    List<Borrow> selectAll(@Param("userId")Long userId);
    int updateByPrimaryKey(Borrow record);
}