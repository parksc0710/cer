package com.cgntv.cer.controller.main;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cgntv.cer.domain.MemberVO;
import com.cgntv.cer.service.MemberService;
import com.cgntv.cer.utils.GetOAuthResource;
import com.cgntv.cer.utils.OAuthKeys;
import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class MainController {

	// 카카오 로그인 REST API 키
	private String KAKAO_CLIENT_ID = OAuthKeys.KAKAO_CLIENT_ID;
	
	private String KAKAO_CLIENT_SECRET = OAuthKeys.KAKAO_CLIENT_SECRET;

	// 카카오 로그인 리다이렉트 URI
	private String KAKAO_REDIRECT_URI = OAuthKeys.KAKAO_REDIRECT_URI;
	
	// 카카오 로그인 인증 서버 URL
	private String KAKAO_AUTH_URL = "https://kauth.kakao.com/oauth/token";
	
	// 카카오 로그인 리소스 서버 URL
	private String KAKAO_RESOURCE_URL = "https://kapi.kakao.com/v1/user/me";
	
	
	// 구글 로그인 REST API 키
	private String GOOGLE_CLIENT_ID = OAuthKeys.GOOGLE_CLIENT_ID;
	
	private String GOOGLE_CLIENT_SECRET = OAuthKeys.GOOGLE_CLIENT_SECRET;
	
	// 구글 로그인 리다이렉트 URI
	private String GOOGLE_REDIRECT_URI = OAuthKeys.GOOGLE_REDIRECT_URI;
		
	// 구글 로그인 인증 서버 URL
	private String GOOGLE_AUTH_URL = "https://www.googleapis.com/oauth2/v4/token";
	
	// 구글 로그인 리소스 서버 URL
	private String GOOGLE_RESOURCE_URL = "https://www.googleapis.com/oauth2/v2/userinfo";
	
	
	// 페이스북 로그인 REST API 키
	private String FACEBOOK_CLIENT_ID = OAuthKeys.FACEBOOK_CLIENT_ID;

	private String FACEBOOK_CLIENT_SECRET = OAuthKeys.FACEBOOK_CLIENT_SECRET;
		
	// 페이스북 로그인 리다이렉트 URI
	private String FACEBOOK_REDIRECT_URI = OAuthKeys.FACEBOOK_REDIRECT_URI;
	
	// 페이스북 로그인 인증 서버 URL
	private String FACEBOOK_AUTH_URL = "https://graph.facebook.com/v3.2/oauth/access_token?";
	
	// 페이스북 로그인 리소스 서버 URL
	private String FACEBOOK_RESOURCE_URL = "https://graph.facebook.com/me?fields=email&access_token=";
		
	
	@Resource
	UserDetailsService customUserDetailsServiceSocial;
	
	@Inject
	private MemberService memberService;
	
	@RequestMapping("/")
	public String main(Authentication authentication) {
		
		return "main/index";
		
	}
	
	
	@RequestMapping("/login")
	public String loginPage(Model model) {
		
		// 카카오 로그인 URL 생성
		String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?"
		          + "client_id=" + KAKAO_CLIENT_ID
		          + "&redirect_uri=" + KAKAO_REDIRECT_URI
		          + "&response_type=code";
		
		// 구글 로그인 URL 생성
		String googleUrl = "https://accounts.google.com/o/oauth2/v2/auth?" 
				+ "scope=https://www.googleapis.com/auth/userinfo.email" 
				+ "&response_type=code" 
				+ "&state=security_token%3D138r5719ru3e1%26url%3Dhttps://oauth2.example.com/token" 
				+ "&client_id=" + GOOGLE_CLIENT_ID
				+ "&redirect_uri=" + GOOGLE_REDIRECT_URI
				+ "&access_type=offline";
		
		// 페이스북 로그인 URL 생성
		String facebookUrl = "https://www.facebook.com/v3.2/dialog/oauth?"
		          + "client_id=" + FACEBOOK_CLIENT_ID
		          + "&redirect_uri=" + FACEBOOK_REDIRECT_URI
		          + "&scope=email"
		          + "&state=test";
		
		
		model.addAttribute("kakaoUrl", kakaoUrl);
		model.addAttribute("googleUrl", googleUrl);
		model.addAttribute("facebookUrl", facebookUrl);
		
		return "login";
		
	}
	
	// 소셜로그인 체크 후 있으면 Spring Security Context 생성. 없으면 기타 로직 추가 
	public boolean socialLoginDo(HttpServletRequest request, String inEmail) throws Exception {
		
		boolean rtn = false;
		
		UserDetails userInfo = customUserDetailsServiceSocial.loadUserByUsername(inEmail); //UserDetailsService에서 유저정보를 불러온다.
	    
		if (userInfo != null) {
		
		    Authentication authentication = new UsernamePasswordAuthenticationToken(userInfo, "OAUTH", userInfo.getAuthorities());
	
		    SecurityContext securityContext = SecurityContextHolder.getContext();
		    securityContext.setAuthentication(authentication);
		    HttpSession session = request.getSession(true);
		    session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		    
		    rtn = true;
	    
		} 
		
		return rtn;
	}
	
	// Oauth2.0으로 이메일 주소 불러오는 메소드
	public String getOAuthResource(String code, String resourceServer) throws UnsupportedEncodingException {

		String clientId = "";
		String clientSecret = "";
		String myRedirectURI = "";
		String authorizationRequestURI = "";
		String resourceOwnerRequestURI = "";
		
		String resourceKey = "";
		String resourceValue = "";
		
		// 리소스 키 설정
		if(resourceServer.equals("kakao")) {
			clientId = KAKAO_CLIENT_ID;
			clientSecret = KAKAO_CLIENT_SECRET;
			myRedirectURI = KAKAO_REDIRECT_URI;
			authorizationRequestURI = KAKAO_AUTH_URL;
			resourceOwnerRequestURI = KAKAO_RESOURCE_URL;
			resourceKey = "kaccount_email";
		} else if(resourceServer.equals("google")) {
			clientId = GOOGLE_CLIENT_ID;
			clientSecret = GOOGLE_CLIENT_SECRET;
			myRedirectURI = GOOGLE_REDIRECT_URI;
			authorizationRequestURI = GOOGLE_AUTH_URL;
			resourceOwnerRequestURI = GOOGLE_RESOURCE_URL;
			resourceKey = "email";
		} else if(resourceServer.equals("facebook")) {
			clientId = FACEBOOK_CLIENT_ID;
			clientSecret = FACEBOOK_CLIENT_SECRET;
			myRedirectURI = FACEBOOK_REDIRECT_URI;
			authorizationRequestURI = FACEBOOK_AUTH_URL;
			resourceOwnerRequestURI = FACEBOOK_RESOURCE_URL;
			resourceKey = "email";
		}
		
		GetOAuthResource getOAuthResource = new GetOAuthResource(clientId, clientSecret, myRedirectURI, authorizationRequestURI, resourceOwnerRequestURI);
		
		// 코드 발급
		System.out.println("code : " + code);
		
		// Access Token // Refresh Token 발급
		JsonNode jsonToken = getOAuthResource.getAccessToken(code);
		String accessToken = jsonToken.get("access_token").toString();
		String refreshToken = "";
		if(jsonToken.has("refresh_token")) {
			refreshToken = jsonToken.get("refresh_token").toString();
		}
		String expiresTime = jsonToken.get("expires_in").toString();
		System.out.println("Access Token : " + accessToken);
		System.out.println("Refresh Token : " + refreshToken);
		System.out.println("Expires Time : " + expiresTime);

		// Access Token으로 사용자 정보 반환
		JsonNode userInfo;
		if(resourceServer.equals("google")) {
			userInfo = getOAuthResource.getUserInfoGet(accessToken);
		} else {
			userInfo = getOAuthResource.getUserInfoPost(accessToken);
		}
		
		System.out.println(userInfo.toString());
		
		resourceValue = userInfo.get(resourceKey).asText();
		
		return resourceValue;
		
	}
	
	
	// 카카오 로그인 콜백
	@RequestMapping(value = "/socialLogin/kakaoLogin")
	public String kakaoLogin(@RequestParam("code") String code, HttpSession session, HttpServletRequest request) throws Exception {

		String socialMail = getOAuthResource(code, "kakao");
		
		if(socialLoginDo(request, socialMail)) {
			return "redirect:/";
		}
		
		return "redirect:login.do";
	}
	
	// 구글 로그인 콜백
	@RequestMapping(value = "/socialLogin/googleLogin")
	public String googleLogin(@RequestParam("code") String code, HttpSession session, HttpServletRequest request) throws Exception {

		String socialMail = getOAuthResource(code, "google");
		
		if(socialLoginDo(request, socialMail)) {
			return "redirect:/";
		}
		
		return "redirect:login.do";
		
	}
	

	// 페이스북 로그인 콜백
	@RequestMapping(value = "/socialLogin/facebookLogin")
	public String facebookLogin(@RequestParam("code") String code, HttpSession session, HttpServletRequest request) throws Exception {

		String socialMail = getOAuthResource(code, "facebook");
		
		if(socialLoginDo(request, socialMail)) {
			return "redirect:/";
		}
		
		return "redirect:login.do";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String register() {
		return "main/register";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signUp(
			@RequestParam("member_email") String member_email,
			@RequestParam("member_pw") String member_pw
		) {
		
		String rtn = "";
		String encodedPw = member_pw;
		MemberVO vo = new MemberVO();
		
		vo.setMember_email(member_email);
		vo.setMember_pw(encodedPw);
		
		try {
			memberService.regist(vo);
			memberService.registGrantUser(member_email);
			rtn = "success";
		} catch (Exception e) {
			e.printStackTrace();
			rtn = "fail";
		} 
		
		return "redirect:/";
	}
}
