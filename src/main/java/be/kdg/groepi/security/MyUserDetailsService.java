package be.kdg.groepi.security;

import be.kdg.groepi.model.User;
import be.kdg.groepi.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    protected UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
        User entityUser = userService.getUserByEmail(username);
        org.springframework.security.core.userdetails.User user =
                new org.springframework.security.core.userdetails.User(entityUser.getEmail(), entityUser.getPassword(), true, true, true, true, getAuthorities());
        return user;
    }

    private List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(1);
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authList;
    }
}
