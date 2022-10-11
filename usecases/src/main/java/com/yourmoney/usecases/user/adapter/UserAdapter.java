package com.yourmoney.usecases.user.adapter;

import com.yourmoney.domain.model.User;

import java.util.Optional;

public interface UserAdapter {
    User create(User user);

    Optional<User> findByUsername(String username);
}
