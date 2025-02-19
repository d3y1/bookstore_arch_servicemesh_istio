package com.github.taosoft.bookstore.domain.security;

import com.github.taosoft.bookstore.domain.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 认证用户的数据仓库
 **/
@Component
public class AuthenticAccountRepository {
    @Autowired
    private AccountServiceClient userService;

    public AuthenticAccount findByUsername(String username) {
        Account account = userService.findByUsername(username);
        return new AuthenticAccount(account);
    }
}
