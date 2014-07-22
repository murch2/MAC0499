/**
 * @author Rodrigo Duarte Louro
 * @dateJul 22, 2014
 */
package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class HTTPPostRequester {

	private String url = "http://10.0.0.2/Requisicao.php"; 

	public JSONObject post(JSONObject jsonParams) {
		System.out.println("DEBUG 1");
		HttpClient httpClient = new DefaultHttpClient(); 
		HttpPost post = new HttpPost(url);
		try {
			StringEntity entityParams = new StringEntity(jsonParams.toString());
			post.setEntity(entityParams);
			System.out.println("DEBUG 2");
			post.addHeader("content-type", "application/x-www-form-urlencoded");
			HttpResponse response = httpClient.execute(post);
			System.out.println("DEBUG 3");
			HttpEntity entity = response.getEntity();
			System.out.println("DEBUG 4");
			StringBuilder sb = new StringBuilder();

			BufferedReader reader = 
					new BufferedReader(new InputStreamReader(entity.getContent()), 65728);
			String line = null;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			System.out.println("finalResult " + sb.toString());
			return new JSONObject(sb.toString());

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void asyncPost(HTTPResponseListener httpResponseListener, JSONObject obj) {
		System.out.println("DEBUG - chegou no async do postrequester: " + obj);
		new HttpPostRequest(httpResponseListener).execute(url, obj.toString());
	}

	private class HttpPostRequest extends AsyncTask<String, String, JSONObject> {

		private HTTPResponseListener responseListener;

		public HttpPostRequest(HTTPResponseListener responseListener) {
			this.responseListener = responseListener; 
		}

		@Override
		protected JSONObject doInBackground(String... params) {
			JSONObject obj = null;
			try {
				obj = new JSONObject(params[1]);
			} catch (JSONException e) {
				System.err.println("FUDEU TUDO AQUI NO ASSINCRONO");
			} 
			System.out.println("DEBUG - dentro da classe estranha obj = " + obj);
			return HTTPPostRequester.this.post(obj);
		}

		protected void onPostExecute(JSONObject result) {
			System.out.println("DEBUG - result na classe estranha : " + result);
			responseListener.onResponse(result); 
		}
	}

}
