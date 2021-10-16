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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.*;

@RestController
@Validated
public class BorrowController {
    @Autowired
    private BorrowService borrowservice;

    @RequestMapping(value = "/borrow", method = RequestMethod.GET)
    @ApiOperation(
        value = "인증이 된 상태에서 빌린 책 조회", notes = "View the books that user borrow",
        authorizations = {
            @Authorization(value="jwt_access_token")
        }
    )
    public ResponseEntity<List<Borrow>> viewBorrow(){
        Authentication authInfo = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authInfo.getPrincipal();
        List<Borrow> emptyList = new ArrayList<>();
        if(principal instanceof User){
            User user = (User)principal;
            Long userId = user.getUserId();
            return new ResponseEntity<>(borrowservice.viewBorrow(userId), HttpStatus.OK);
        }
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
    public ResponseEntity delBorrow(
            @ApiParam(value = "제거할 주문 id")
            @PathVariable("borrowId") @Min(1) Long borrowId){
        borrowservice.delBorrow(borrowId);
        HashMap <String, String> response = new HashMap<>();
        response.put("message", "borrowId가 " + borrowId + "인 주문을 제거하였습니다.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
