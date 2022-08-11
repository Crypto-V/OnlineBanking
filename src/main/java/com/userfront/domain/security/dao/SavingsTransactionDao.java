package com.userfront.domain.security.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.SavingsTransaction;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction, Long> {

    List<SavingsTransaction> findAll();
}

