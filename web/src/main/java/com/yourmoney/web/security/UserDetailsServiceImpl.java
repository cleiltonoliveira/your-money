package com.yourmoney.web.security;

import com.yourmoney.usecases.user.UserFinder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserFinder userFinder;

    public UserDetailsServiceImpl(UserFinder userFinder) {
        this.userFinder = userFinder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var userAuth = userFinder.findByUsername(username);

        List<GrantedAuthority> authorityListAdmin = AuthorityUtils
                .createAuthorityList("ROLE_ADMIN");

        return new User(
                userAuth.getUsername(),
                userAuth.getPassword(),
                authorityListAdmin);
    }
}