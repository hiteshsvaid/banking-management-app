package com.citi.banking.BankingManagement.repositories;

import com.citi.banking.BankingManagement.entities.Account;
import com.citi.banking.BankingManagement.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "accounts", path = "accounts")
public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByCustomer(Customer customer);
}
