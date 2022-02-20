package com.example.dev_j140_all.database;

import com.example.dev_j140_all.models.Account;
import com.example.dev_j140_all.models.User;

import java.util.ArrayList;

public interface DatabaseStorage extends AutoCloseable {
    boolean isLoginFree(String login);
    boolean saveUser(User user);
    boolean saveAccount(Account account);
    boolean checkUserLoginAndPassword(String login, String password);
    ArrayList<Account> loadAccounts();
}
