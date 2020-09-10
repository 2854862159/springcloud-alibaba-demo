package com.cloud.sso.config;

import com.cloud.sso.model.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * ClassName: UserDetailsService <br/>
 * Description: <br/>
 * date: 2020/9/10 5:24 下午<br/>
 *
 * @author tooru<br />
 */
@Component
public class AccountUserDetailsService implements UserDetailsService {

    @Autowired
    UserRep rep;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.cloud.sso.model.User> byId = rep.findById(username);
        if (byId.isPresent()) {
            com.cloud.sso.model.User user = byId.get();
            System.out.println("登录成功, " + username);
            return new User(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        }
        throw new UsernameNotFoundException("Could not find " + username);
    }
}
