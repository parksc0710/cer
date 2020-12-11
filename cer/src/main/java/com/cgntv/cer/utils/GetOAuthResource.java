package com.cgntv.cer.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetOAuthResource {
	
	private String clientId;
	private String clientSecret;
	private String myRedirectURI;
	private String authorizationRequestURI;
	private String resourceOwnerRequestURI;
	
	public GetOAuthResource(String clientId, String clientSecret, String myRedirectURI,
			String authorizationRequestURI, String resourceOwnerRequestURI) {
		
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.myRedirectURI = myRedirectURI;
		this.authorizationRequestURI = authorizationRequestURI;
		this.resourceOwnerRequestURI = resourceOwnerRequestURI;
		
	}
	
	public JsonNode getAccessToken(String autorize_code) {
		
		final String requestUrl = authorizationRequestURI;

		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", clientId)); // REST API KEY
		postParams.add(new BasicNameValuePair("redirect_uri", myRedirectURI)); // 리다이렉트 URI
		postParams.add(new BasicNameValuePair("code", autorize_code)); // 로그인 과정중 얻은 code 값
		if(!(clientSecret == null || clientSecret.equals(""))) {
			postParams.add(new BasicNameValuePair("client_secret", clientSecret));
		}

		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(requestUrl);
		JsonNode returnNode = null;

		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));
			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode();

			System.out.println("\nSending 'POST' request to URL : " + requestUrl);
			System.out.println("Post parameters : " + postParams);
			System.out.println("Response Code : " + responseCode);

			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}

		return returnNode;

	}
	
	public JsonNode getUserInfoPost(String autorize_code) {

		String requestUrl = resourceOwnerRequestURI.equals("https://graph.facebook.com/me?fields=email&access_token=") ? resourceOwnerRequestURI + autorize_code.replaceAll("\"", "") : resourceOwnerRequestURI;
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(requestUrl);

		// add header
		post.addHeader("Authorization", "Bearer " + autorize_code);

		JsonNode returnNode = null;

		try {
			final HttpResponse response = client.execute(post);
			
			final int responseCode = response.getStatusLine().getStatusCode();

			System.out.println("\nSending 'POST' request to URL : " + requestUrl);
			System.out.println("Response Code : " + responseCode);

			// JSON 형태 반환값 처리
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}
		return returnNode;

	}
	
	public JsonNode getUserInfoGet(String autorize_code) {

		String requestUrl = resourceOwnerRequestURI.equals("https://graph.facebook.com/me?fields=email&access_token=") ? resourceOwnerRequestURI + autorize_code.replaceAll("\"", "") : resourceOwnerRequestURI;
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpGet get = new HttpGet(requestUrl);

		JsonNode returnNode = null;
		
		// add header
		get.addHeader("Authorization", "Bearer " + autorize_code);

		try {
			final HttpResponse response = client.execute(get);
			final int responseCode = response.getStatusLine().getStatusCode();
			
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());
			
			System.out.println("\nSending 'GET' request to URL : " + requestUrl);
			System.out.println("Response Code : " + responseCode);


		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}
		return returnNode;

	}
	
}

