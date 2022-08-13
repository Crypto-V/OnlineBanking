package com.userfront.service.UserServiceImpl;

import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.SavingsAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {
    PrimaryAccount pAcc;
    SavingsAccount sAcc;

    @Test
    void createPrimaryAccount() {
        pAcc = new PrimaryAccount();
    }

    @Test
    void createSavingsAccount() {
        sAcc = new SavingsAccount();
    }

    @Test
    void deposit() {

    }

    @Test
    void withdraw() {
    }
}