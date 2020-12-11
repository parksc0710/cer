package com.cgntv.cer.persistence;

import com.cgntv.cer.domain.MemberVO;

public interface MemberDAO {
	
	public String getTime();
	
	public void insertMember(MemberVO vo);
	
	public MemberVO selectMember(Integer member_id) throws Exception;
	
	public MemberVO selectMemberWithPw(String user_id, String user_pw) throws Exception;
	
	public void insertGrantUser(String user_id);

	public Integer selectMemberID(String user_id) throws Exception;
	
	public Integer selectMemberEmail(String user_email) throws Exception;

}
