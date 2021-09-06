package com.example.library.controller;

import com.example.library.model.Borrow;
import com.example.library.service.BorrowService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BorrowController {
    @Autowired
    private BorrowService borrowservice;

    @RequestMapping(value = "/borrow/{userId}", method = RequestMethod.GET)
    @ApiOperation(
        value = "빌린 책 조회", notes = "View the books that user borrow",
        authorizations = {
            @Authorization(value="jwt_access_token")
        }
    )
    public ResponseEntity<List<Borrow>> viewBorrow(@ApiParam(value = "유저 id") @PathVariable("userId")Long userId){
        return new ResponseEntity<>(borrowservice.viewBorrow(userId), HttpStatus.OK);
    }

    @RequestMapping(value = "/borrow", method = RequestMethod.POST)
    @ApiOperation(
        value = "책을 빌리는 order 추가", notes = "borrow the book",
        authorizations = {
            @Authorization(value="jwt_access_token")
        }
    )
    public ResponseEntity<Borrow> addBorrow(@ApiParam(value = "borrow 정보") @RequestBody Borrow borrow){
        if(borrow.getUserId() == null)
            return new ResponseEntity<>(borrow, HttpStatus.BAD_REQUEST);
        if(borrow.getBookId() == null)
            return new ResponseEntity<>(borrow, HttpStatus.BAD_REQUEST);
        borrowservice.addBorrow(borrow);
        return new ResponseEntity<>(borrow, HttpStatus.OK);
    }

    @RequestMapping(value = "/borrow/{borrowId}", method = RequestMethod.DELETE)
    @ApiOperation(
        value = "책을 반납하는 api", notes = "return the book to the library",
        authorizations = {
            @Authorization(value="jwt_access_token")
        }
    )
    public ResponseEntity<String> delBorrow(@ApiParam(value = "제거할 주문 id") @PathVariable("borrowId")Long borrowId){
        if(borrowId == null)
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        int delCount = borrowservice.delBorrow(borrowId);
        if(delCount > 0)
            return new ResponseEntity<>("", HttpStatus.OK);
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }
}
