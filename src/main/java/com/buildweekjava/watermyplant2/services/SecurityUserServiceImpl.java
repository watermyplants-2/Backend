package com.buildweekjava.watermyplant2.services;

import com.buildweekjava.watermyplant2.exceptions.ResourceNotFoundException;
import com.buildweekjava.watermyplant2.models.User;
import com.buildweekjava.watermyplant2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service(value = "securityUserService")
public class SecurityUserServiceImpl
        implements UserDetailsService
{

    @Autowired
    private UserRepository userrepos;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String s)
            throws
            ResourceNotFoundException
    {
        User user = userrepos.findByUsername(s.toLowerCase());
        if (user == null)
        {
            throw new ResourceNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.getAuthority());
    }
}
