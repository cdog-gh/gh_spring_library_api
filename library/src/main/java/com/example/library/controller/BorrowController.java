package com.example.library.controller;

import com.example.library.model.Borrow;
import com.example.library.model.BorrowInfo;
import com.example.library.model.User;
import com.example.library.service.BorrowService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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
        Authentication authInfo = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authInfo.getPrincipal();
        List<Borrow> emptyList = new ArrayList<>();
        if(principal instanceof User){
            User user = (User)principal;

            //admin 인 경우
            if(user.getUserRoleName().compareTo("ROLE_ADMIN") == 0)
                return new ResponseEntity<>(borrowservice.viewBorrow(userId), HttpStatus.OK);

            //admin 이 아닌 경우 여기로 넘어온다.
            if(user.getUserId().longValue() == userId.longValue())
                return new ResponseEntity<>(borrowservice.viewBorrow(userId), HttpStatus.OK);
        }

        //user 가 다른 사람의 borrow 목록을 보는 요청은 거부한다.
        return new ResponseEntity<>(emptyList, HttpStatus.FORBIDDEN);
    }

    @RequestMapping(value = "/borrow", method = RequestMethod.POST)
    @ApiOperation(
        value = "책을 빌리는 order 추가", notes = "borrow the book",
        authorizations = {
            @Authorization(value="jwt_access_token")
        }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Borrow> addBorrow(
            @ApiParam(value = "borrow 정보")
            @RequestBody @Valid BorrowInfo borrowInfo){
        Borrow borrow = new Borrow();
        borrow.setBookId(borrowInfo.getBookId());
        borrow.setUserId(borrowInfo.getUserId());
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> delBorrow(@ApiParam(value = "제거할 주문 id") @PathVariable("borrowId")Long borrowId){
        if(borrowId == null)
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        int delCount = borrowservice.delBorrow(borrowId);
        if(delCount > 0)
            return new ResponseEntity<>("", HttpStatus.OK);
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }
}
