package com.vti.testing.controller;

import com.vti.testing.dto.AccountDTO;
import com.vti.testing.entity.Account;
import com.vti.testing.form.account.AccountFilterForm;
import com.vti.testing.form.account.CreatingAccountForm;
import com.vti.testing.form.account.UpdatingAccountForm;
import com.vti.testing.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/accounts")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<AccountDTO> getAllAccounts(@PageableDefault(page = 0,size = 10,sort = "id",direction = Sort.Direction.DESC) Pageable pageable, AccountFilterForm form) {
        Page<Account> accountsPage = accountService.getAllAccounts(pageable, form);
        List<Account> accounts = accountsPage.getContent();
        List<AccountDTO> accountDTOS = modelMapper.map(accounts, new TypeToken<List<AccountDTO>>() {
        }.getType());
        return new PageImpl<>(accountDTOS, pageable, accountsPage.getTotalElements());
    }

    @GetMapping(value = "/{id}")
    public AccountDTO getAccountById(@PathVariable(name = "id") int id) {
        Account account = accountService.getAccountById(id);
        return modelMapper.map(account, AccountDTO.class);
    }

    @PostMapping
    public void createAccount(@RequestBody CreatingAccountForm form){
        accountService.createAccount(form);
    }
    @PutMapping("/{id}")
    public void updateAccount(@RequestBody UpdatingAccountForm form,@PathVariable(name = "id")int id){
        form.setId(id);
        accountService.updateAccount(form);
    }


    @DeleteMapping(value = "/{ids}")
        public void deleteAccounts(@PathVariable(name = "ids") List<Integer> ids){
            accountService.deleteAccounts(ids);
    }

}
