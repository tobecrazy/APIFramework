package main.java.cn.dbyl.APIUtils;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by young on 5/9/16.
 */
public class HttpUtils {
	public static final OkHttpClient client = new OkHttpClient();
	public static HttpUtils httpUtils;
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	/**
	 * avoid to invoke this constructor
	 */
	private HttpUtils() {
	}

	/**
	 * only one instance
	 *
	 * @return
	 */
	public static HttpUtils getInstance() {
		if (httpUtils == null) {
			synchronized (HttpUtils.class) {

				if (httpUtils == null) {
					httpUtils = new HttpUtils();
				}

			}
		}
		return httpUtils;
	}

	/**
	 * @param url
	 * @return
	 */
	public InputStream getImageStream(String url) {
		try {
			Request request = new Request.Builder().url(url).build();
			Response response = client.newCall(request).execute();
			InputStream inputStream = response.body().byteStream();
			return inputStream;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public void getResponse(String requestUrl) {
		Request.Builder builder = new Request.Builder();

		builder.get().url(requestUrl);
		Request request = builder.build();
		Response response = null;
		try {
			response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				System.out.println(response.body().string());
			} else {

			}
			response.code();
			response.message();
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
