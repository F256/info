package com.web.controllers;

import com.web.util.HttpRequest;

import javax.servlet.ServletContext;  
import javax.servlet.ServletOutputStream;  
import javax.servlet.http.HttpServletResponse;  
import java.io.*; 

import org.json.JSONArray;
import org.json.JSONObject;

public class KandyController {

	public static String API_BASE_URL = "https://api.kandy.io/v1.3/";

	public static String apiKey = "DAKa2429df2e6bb4e458696b5e2dafb7168";//DAK7d5f29ce88d848a98d0caa2f5f02a87f //DAKa2429df2e6bb4e458696b5e2dafb7168

	public static String apiSecret = "DAS41b9f5330911488e9e8978f8a1d50007";//DAS3fd1b92dcd044199bb9cb34e76c4d6e7 //DAS41b9f5330911488e9e8978f8a1d50007

	public static String domainAccessToken;
	
	public static String userAccessToken;
	
	public static void main(String[] args) {
		//
		/*String param = "user_id=f100&user_email=f100@qq.com&user_country_code=CN&user_first_name=胡&user_last_name=熊";
		createUser(param);*/
		//
		getDomainsUsers();
		//billingDomainsCdrs();
		//
		//getUserAccessToken("f100");
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
	
	public static void billingDomainsCdrs(){
		//
		domainAccessToken = getDomainAccessToken();
		String url = API_BASE_URL + "domains/billing/domains/cdrs";
		String param = "key="+domainAccessToken+"&start_date=2016-12-01 12:00:00:000&end_date=2016-12-15 12:00:00:000";
		// 接收到的JSON字符串
		String response = HttpRequest.sendGet(url, param);
		System.out.println(response);
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

	private ServletContext servletContext;
    public void setServletContext(ServletContext servletContext) {  
        this.servletContext = servletContext;  
    }
	
	public void fileDownload(HttpServletResponse response){  
        //获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载  
        String path = servletContext.getRealPath("/");  
  
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        response.setContentType("multipart/form-data");
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
        response.setHeader("Content-Disposition", "attachment;fileName="+"a.pdf");  
        ServletOutputStream out;  
        //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)  
        File file = new File(path + "download/" + "download.pdf");
  
        try {  
            FileInputStream inputStream = new FileInputStream(file);  
  
            //3.通过response获取ServletOutputStream对象(out)  
            out = response.getOutputStream();  
  
            int b = 0;  
            byte[] buffer = new byte[512];  
            while (b != -1){  
                b = inputStream.read(buffer);  
                //4.写到输出流(out)中  
                out.write(buffer,0,b);  
            }  
            inputStream.close();  
            out.close();  
            out.flush();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  

}
