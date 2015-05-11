package com.martin.util;

import java.net.SocketTimeoutException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class HttpHelper {
	
//	AsyncHttpClient client;
	
//	private String sendGetResponse;

    private final DefaultHttpClient httpClient;

    public HttpHelper() {
    	
        HttpParams myParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout( myParams, 10000 );
        HttpConnectionParams.setSoTimeout( myParams, 10000 );
        httpClient = new DefaultHttpClient( myParams );

    }

    public  String sendGet( String url ) {

    	String result="";
    	
//    	Exception exception = null;
//		do {
			try{
		        HttpGet httpGet = new HttpGet( url );
		        HttpResponse response = httpClient.execute( httpGet );
		        result = EntityUtils.toString( response.getEntity() );

	    	}
			catch(ConnectTimeoutException ce){
	    		Log.e("HttpHelper","sendGet connect timeout: "+ce + " ... url: "+url);
			}
			catch(HttpHostConnectException e){
	    		Log.e("HttpHelper","sendGet connection problem: "+e + " ... url: "+url);
			}
	    	catch(Exception ex){
	    		Log.e("HttpHelper","sendGet: "+ex + " ... url: "+url);
	    	}
			
//		}
//		while (exception != null
//				&& exception instanceof SocketTimeoutException);
    	
        return result;
    }
    
	public String getResponse(String url) {

		Exception exception = null;
		do {
			try {
				return sendGet(url);
			} catch (Exception e) {
				exception = e;
			}
		} while (exception != null
				&& exception instanceof SocketTimeoutException);

		if (exception != null) {
			exception.printStackTrace();
		}

		return null;
	}

	public <T> T getResponse(Class<T> clazz, String path) {

		URI uri;
		try {
			uri = new URI(path);
			String response = getResponse(uri.toString());
			
			Log.i("HttpHelper","Response: "+response);

			Gson gson = new GsonBuilder().create();
			return gson.fromJson(response, clazz);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public static boolean isOnline(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {

		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
