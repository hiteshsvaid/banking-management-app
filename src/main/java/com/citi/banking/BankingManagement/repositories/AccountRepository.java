package com.citi.banking.BankingManagement.repositories;

import com.citi.banking.BankingManagement.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
