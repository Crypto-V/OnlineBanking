package com.userfront.domain.security.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.userfront.domain.PrimaryTransaction;
import org.springframework.stereotype.Repository;

@Repository
public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction, Long> {

    List<PrimaryTransaction> findAll();
}
