package com.example.android.testing.espresso.HttpRequest;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
	private static final int HTTP_CONNECTION_TIMEOUT = 3000;
	private static final int HTTP_READ_TIMEOUT = 3000;

	public static String getUrlResponseMessage(String url) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
			connection.setConnectTimeout(HTTP_CONNECTION_TIMEOUT);
			connection.setReadTimeout(HTTP_READ_TIMEOUT);
			connection.connect();
			return connection.getResponseMessage();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
