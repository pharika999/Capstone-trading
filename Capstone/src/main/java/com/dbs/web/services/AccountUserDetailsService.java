package com.dbs.web.services;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Custodian;
import com.dbs.web.repository.CustodianRepository;


@Service
public class AccountUserDetailsService implements UserDetailsService{

	@Autowired
	private CustodianRepository custRepo;
	
	 @Autowired
	private PasswordEncoder encode;
	@Override
	public UserDetails loadUserByUsername(String custId) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Custodian> optional = this.custRepo.findById(custId);
        return optional.map(account->{
            User user = new User(account.getCustodianid(), encode.encode(account.getPassword()),
           Arrays.asList(new SimpleGrantedAuthority("USER")));
            return user;
        }).orElseThrow(()-> new UsernameNotFoundException("Invalid Credentials"));
	}
	
	  @Bean
	    public PasswordEncoder encoder()
	    {
	    	return new BCryptPasswordEncoder();
	    }

}
