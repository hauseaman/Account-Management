package com.vti.testing.service;

import com.vti.testing.entity.Account;
import com.vti.testing.form.account.AccountFilterForm;
import com.vti.testing.form.account.CreatingAccountForm;
import com.vti.testing.form.account.UpdatingAccountForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IAccountService extends UserDetailsService {
    Page<Account> getAllAccounts(Pageable pageable, AccountFilterForm form);

    Account getAccountById(int id);

    Account getAccountByUsername(String username);

    void createAccount(CreatingAccountForm form);
    void updateAccount(UpdatingAccountForm form);

    void deleteAccounts(List<Integer> accounts);
}
