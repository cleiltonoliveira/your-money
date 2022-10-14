package com.yourmoney.usecases.user;

import com.yourmoney.domain.model.User;
import com.yourmoney.usecases.user.adapter.UserAdapter;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UserCreator {
    private final UserAdapter userAdapter;

    @Inject
    public UserCreator(UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }

    public User create(User user) {
        return userAdapter.create(user);
    }
}
