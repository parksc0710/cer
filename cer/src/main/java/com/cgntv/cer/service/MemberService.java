package com.cgntv.cer.service;

import com.cgntv.cer.domain.MemberVO;

public interface MemberService {
	
	public void regist(MemberVO member) throws Exception;
	
	public void registGrantUser(String user_id) throws Exception;
	
	public MemberVO selectOne(Integer member_id) throws Exception;
	
	public MemberVO selectMemberWithPw(String user_id, String user_pw) throws Exception;
}
