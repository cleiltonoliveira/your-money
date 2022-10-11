package com.yourmoney.usecases.user;

import com.yourmoney.domain.model.User;
import com.yourmoney.usecases.exception.ResourceNotFoundException;
import com.yourmoney.usecases.user.adapter.UserAdapter;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UserFinder {
    private final UserAdapter userAdapter;

    @Inject
    public UserFinder(UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }

    public User findByUsername(String username) {

        var user = userAdapter.findByUsername(username);

        return user.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
    }
}
