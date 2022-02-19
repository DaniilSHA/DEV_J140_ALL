package com.example.dev_j140_all.database;

import com.example.dev_j140_all.models.User;

public interface DatabaseStorage extends AutoCloseable {
    boolean isLoginFree(String login);
    boolean saveUser(User user);
}
