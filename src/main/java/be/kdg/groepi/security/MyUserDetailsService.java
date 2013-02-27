package be.kdg.groepi.security;

import be.kdg.groepi.model.User;
import be.kdg.groepi.service.UserService;
import be.kdg.groepi.utils.DateUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
        UserService.createUser(new User("Django", "django@candyland.com", "Django", DateUtil.dateToLong(24,3,1988,0,0,0)));
        User entityUser = UserService.getUserByEmail(username);
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
