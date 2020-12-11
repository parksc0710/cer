package com.cgntv.cer.controller.member;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cgntv.cer.domain.MemberVO;
import com.cgntv.cer.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Inject
	private MemberService service;
	
	@Inject
	private Md5PasswordEncoder pe;
	

}
