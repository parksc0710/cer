package com.cgntv.cer.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.cgntv.cer.domain.MemberVO;
import com.cgntv.cer.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	@Override
	public void regist(MemberVO member) throws Exception {
		dao.insertMember(member);
	}

	@Override
	public MemberVO selectOne(Integer member_id) throws Exception {
		return dao.selectMember(member_id);
	}

	@Override
	public MemberVO selectMemberWithPw(String user_id, String user_pw) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registGrantUser(String user_id) throws Exception {
		dao.insertGrantUser(user_id);
	}


}
