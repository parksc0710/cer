package com.cgntv.cer.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cgntv.cer.persistence.MemberDAO;

@Service
public class CustomUserDetailsServiceSocial implements UserDetailsService {

	@Inject
	MemberDAO dao;
	
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		UserDetails userInfo = null;
		try {
			int member_id = dao.selectMemberEmail(userEmail);
			userInfo = (UserDetails) dao.selectMember(member_id); //디비 정보를 불러와 유저정보 조
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userInfo;
	}

}
