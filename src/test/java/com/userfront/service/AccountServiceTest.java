//package com.userfront.service;
//
//import com.userfront.domain.PrimaryAccount;
//import com.userfront.domain.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//class AccountServiceTest {
//    @Autowired
//    private AccountService underTest;
//
//    @Test
//    void createPrimaryAccount() {
//        //given
//        PrimaryAccount primaryAccount = new PrimaryAccount();
//        underTest.createPrimaryAccount();
//        //when
//        User user = new User();
//        primaryAccount = user.getPrimaryAccount();
//
//        //then
//        assertThat(primaryAccount).toString();
//
//    }
//
//    @Test
//    void createSavingsAccount() {
//    }
//
//    @Test
//    void deposit() {
//    }
//
//    @Test
//    void withdraw() {
//    }
//}