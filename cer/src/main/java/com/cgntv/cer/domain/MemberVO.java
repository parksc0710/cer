package com.cgntv.cer.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MemberVO implements UserDetails{

	private int member_id;
	private String member_email;
	private String member_pw;
	private String member_grant;
	private String member_name;
	private Date create_date;
	private Date update_date;
	private boolean act_flg;
	private boolean del_flg;
	
	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	
	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_grant() {
		return member_grant;
	}

	public void setMember_grant(String member_grant) {
		this.member_grant = member_grant;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public boolean isAct_flg() {
		return act_flg;
	}

	public void setAct_flg(boolean act_flg) {
		this.act_flg = act_flg;
	}

	public boolean isDel_flg() {
		return del_flg;
	}

	public void setDel_flg(boolean del_flg) {
		this.del_flg = del_flg;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		String roleGrant = "ROLE_" + member_grant;
		
		GrantedAuthority myGrant = new SimpleGrantedAuthority(roleGrant);
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		authorities.add(myGrant);
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return member_pw;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return member_email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
