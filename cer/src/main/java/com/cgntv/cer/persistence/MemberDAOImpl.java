package com.cgntv.cer.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cgntv.cer.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.cgntv.cer.MemberMapper";
	
	
	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace + ".getTime");
	}

	@Override
	public void insertMember(MemberVO vo) {
		
		sqlSession.insert(namespace + ".insertMember", vo); 
		
	}

	@Override
	public MemberVO selectMember(Integer member_id) throws Exception {
		return (MemberVO) sqlSession.selectOne(namespace + ".selectMember", member_id); 
	}

	@Override
	public MemberVO selectMemberWithPw(String user_id, String user_pw) throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("user_id", user_id);
		paramMap.put("user_pw", user_pw);
			
		return (MemberVO) sqlSession.selectOne(namespace + ".selectMemberWithPw", paramMap); 
		
	}

	@Override
	public void insertGrantUser(String user_id) {
		sqlSession.insert(namespace + ".insertGrantUser", user_id); 
	}

	@Override
	public Integer selectMemberID(String user_id) throws Exception {
		int member_id = 0;
		
		MemberVO tmpVO = (MemberVO) sqlSession.selectOne(namespace + ".selectMemberID", user_id);
		
		member_id = tmpVO.getMember_id();
		
		return member_id;
	}

	@Override
	public Integer selectMemberEmail(String user_email) throws Exception {
		int member_id = 0;
		
		MemberVO tmpVO = (MemberVO) sqlSession.selectOne(namespace + ".selectMemberEmail", user_email);
		
		member_id = tmpVO.getMember_id();
		
		return member_id;
	}

}
