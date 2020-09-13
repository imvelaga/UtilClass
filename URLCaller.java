package com.geoilandgas.automationservice.util;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class URLCaller {

	// This is an example class to call URL endpoint with GET and POST methods.
	// You can change it or use other several approaches for the same.
	
	private static final int BUFFER_SIZE = 4096;
		
	public static String getRequest(String urlString) {
		String out = "";
			try {
				URLConnection conn = new URL(urlString).openConnection();
				InputStream inputStream = conn.getInputStream();
				if (inputStream != null) {
					out = IOUtils.toString(inputStream, "UTF-8");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	   return out;
	}
	
	public static String getRequest(String urlString, Map<String, Object> headers) {
		String out = "";
		int attemps = 0;
			HttpURLConnection conn = null;
			try {
				conn = (HttpURLConnection)new URL(urlString).openConnection();
				for (String header : headers.keySet())
				{
					conn.setRequestProperty(header, headers.get(header).toString());
				}			
				
				InputStream inputStream = conn.getInputStream();
				if (inputStream != null) {
					out = IOUtils.toString(inputStream, "UTF-8");
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
								
			return out;
	}
	
	public static String postRequest(String urlString, Map<String, Object> headers, String body) {
		int index = 0;
		String out = "";
			try {
				HttpURLConnection conn = (HttpURLConnection)new URL(urlString).openConnection();
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				
				for (String header : headers.keySet())
				{
					conn.setRequestProperty(header, headers.get(header).toString());
				}	
				
				DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
				wr.writeBytes(body);
				wr.flush();
				wr.close();
				InputStream inputStream = conn.getInputStream();
				if (inputStream != null) {
					out = IOUtils.toString(inputStream, "UTF-8");
				}
			} catch (Exception e) {
			e.printStackTrace();
				index++;
			}
		return out;
	}

}
