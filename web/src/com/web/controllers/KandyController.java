package com.web.controllers;

import com.web.util.HttpRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/kandy")
public class KandyController {

	public static String API_BASE_URL = "https://api.kandy.io/v1.2/";

	public static String apiKey = "DAKa2429df2e6bb4e458696b5e2dafb7168";

	public static String apiSecret = "DAS41b9f5330911488e9e8978f8a1d50007";

	public static String domainAccessToken;
	
	public static String userAccessToken;
	@RequestMapping("/index")
	public static void main(String[] args) {
		//
		/*String param = "user_id=f100&user_email=f100@qq.com&user_country_code=CN&user_first_name=胡&user_last_name=熊";
		createUser(param);*/
		//
		//getDomainsUsers();
		//
		getUserAccessToken("f100");
/*		String param = "device_native_id=12345678901234&device_family=iphone&device_name=iphone6s&client_sw_version=0102001&device_os_version=0.1";
		createDevice("f100", param);*/
	}

	public static String getDomainAccessToken() {
		//
		if(domainAccessToken != null){
			return domainAccessToken;
		}
		//
		String url = API_BASE_URL + "domains/accesstokens";
		String param = "key=" + apiKey + "&domain_api_secret=" + apiSecret;
		// 接收到的JSON字符串
		String response = HttpRequest.sendGet(url, param);
		// System.out.println(response);
		// 接收到的JSON字符串
		JSONObject json = new JSONObject(response);
		int status = json.getInt("status");
		if(status!=0){
			String message = json.getString("message");
			System.out.println(message);
			System.exit(0);
		}
		JSONObject result = json.getJSONObject("result");
		domainAccessToken = result.getString("domain_access_token");
		System.out.println(domainAccessToken);
		return domainAccessToken;
	}

	public static void getDomainsUsers() {
		//
		domainAccessToken = getDomainAccessToken();
		//
		String url = API_BASE_URL + "domains/users";
		String param = "key=" + domainAccessToken;
		// 接收到的JSON字符串
		String response = HttpRequest.sendGet(url, param);
		// System.out.println(response);
		// 接收到的JSON字符串
		JSONObject json = new JSONObject(response);
		int status = json.getInt("status");		
		if(status!=0){
			String message = json.getString("message");
			System.out.println(message);
			System.exit(0);
		}		
		JSONObject result = json.getJSONObject("result");
		// System.out.println(result);
		JSONArray users = result.getJSONArray("users");
		// System.out.println(users);
		for (int i = 0; i < users.length(); i++) {
			JSONObject user = (JSONObject) users.get(i);
			// String userName=(String) user.get("user_id");
			System.out.println(user);
		}
		//
	}

	public static void createUser(String param) {
		//
		domainAccessToken = getDomainAccessToken();
		String url = API_BASE_URL + "domains/users/user_id?key=" + domainAccessToken;
		// 接收到的JSON字符串
		String response = HttpRequest.sendPost(url, param);
		System.out.println(response);
		// 接收到的JSON字符串
		JSONObject json = new JSONObject(response);
		int status = json.getInt("status");		
		if(status!=0){
			String message = json.getString("message");
			System.out.println(message);
			System.exit(0);
		}
		JSONObject result = json.getJSONObject("result");
		System.out.println(result);
	}
	
	public static String getUserAccessToken(String userId){
		//
		if(userAccessToken != null){
			return userAccessToken;
		}
		//
		String url = API_BASE_URL + "domains/users/accesstokens";
		String param = "key=" + apiKey + "&domain_api_secret=" + apiSecret+"&user_id="+userId;
		// 接收到的JSON字符串
		String response = HttpRequest.sendGet(url, param);
		//System.out.println(response);System.exit(0);
		// 接收到的JSON字符串
		JSONObject json = new JSONObject(response);
		int status = json.getInt("status");
		if(status!=0){
			String message = json.getString("message");
			System.out.println(message);
			System.exit(0);
		}
		JSONObject result = json.getJSONObject("result");
		userAccessToken = result.getString("user_access_token");
		System.out.println(userAccessToken);
		return userAccessToken;
	}

	public static void createDevice(String userId, String param) {
		//
		String userAccessToken = getUserAccessToken(userId);
		String url = API_BASE_URL + "users/devices?key=" + userAccessToken;
		// 接收到的JSON字符串
		String response = HttpRequest.sendPost(url, param);
		System.out.println(response);
		// 接收到的JSON字符串
		JSONObject json = new JSONObject(response);
		int status = json.getInt("status");		
		if(status!=0){
			String message = json.getString("message");
			System.out.println(message);
			System.exit(0);
		}
		JSONObject result = json.getJSONObject("result");
		System.out.println(result);
		//
	}

}
