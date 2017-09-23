package main.java.cn.dbyl.tests;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
/*
 * ====================================================================
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import main.java.cn.dbyl.APIUtils.JsonUtils;
import main.java.cn.dbyl.beans.ZhihuAPIBean;

public class QuickStart {

	public static String host = "news-at.zhihu.com";
	private static HttpClientConnectionManager connMrg;

	public static void main(String[] args) throws Exception {

		ZhihuAPIBean zhihu = null;
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpClientContext context = HttpClientContext.create();
		connMrg = new BasicHttpClientConnectionManager();
		HttpRoute route = new HttpRoute(new HttpHost(host, 80));
		// Request new connection. This can be a long process
		ConnectionRequest connRequest = connMrg.requestConnection(route, null);
		// Wait for connection up to 10 sec
		HttpClientConnection conn = connRequest.get(10, TimeUnit.SECONDS);
		try {
			// If not open
			if (!conn.isOpen()) {
				// establish connection based on its route info
				connMrg.connect(conn, route, 1000, context);
				// and mark it as route complete
				connMrg.routeComplete(conn, route, context);
			}
			// Do useful things with the connection.
		} finally {
			connMrg.releaseConnection(conn, null, 1, TimeUnit.MINUTES);
		}
		try {
			URI uri = new URIBuilder().setScheme("http").setHost(host)
					.setPath("/api/7/prefetch-launch-images/1080*1920")
					// .setParameter("q", "httpclient")
					// .setParameter("btnG", "Google Search")
					// .setParameter("aq", "f")
					// .setParameter("oq", "")
					.build();
			RequestConfig config = RequestConfig.custom().setSocketTimeout(1000).setConnectTimeout(1000).build();
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setConfig(config);
			httpGet.setHeader("Content-Type", "application/json; charset=UTF-8");
			httpGet.addHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36");
			System.out.println(httpGet.getURI());
			CloseableHttpResponse response1 = httpclient.execute(httpGet, context);
			System.out.println(response1.getStatusLine());
			HttpHost target = context.getTargetHost();
			List<URI> redirectLocations = context.getRedirectLocations();
			URI location = URIUtils.resolve(httpGet.getURI(), target, redirectLocations);
			System.out.println("Final HTTP location: " + location.toASCIIString());
			System.out.println(response1.getStatusLine());
			HttpEntity entity = response1.getEntity();
			try {
				if (entity != null) {
					long len = entity.getContentLength();
					if (len != -1 && len < 2048) {
						String str = EntityUtils.toString(entity);
						System.out.println(str);
						zhihu = (ZhihuAPIBean) JsonUtils.getInstance().parseStringToBean(str, ZhihuAPIBean.class);
					} else {
						// Stream content out

					}
				}
			} finally {
				response1.close();
			}
			EntityUtils.consume(entity);
			System.out.println(zhihu.getCreatives().get(0).getUrl());
			downloadPicture(zhihu.getCreatives().get(0).getUrl());
			HttpPost httpPost = new HttpPost("http://httpbin.org/post");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("username", "vip"));
			nvps.add(new BasicNameValuePair("password", "secret"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try {
				System.out.println(response2.getStatusLine());
				HttpEntity entity2 = response2.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				EntityUtils.consume(entity2);
			} finally {
				response2.close();
			}
		} finally {
			httpclient.close();
		}
	}

	public static void downloadPicture(String picUrl) {
		Thread download = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					File file = new File("zhihu.jpg");
					OutputStream os = new FileOutputStream(file);
					// 创建一个url对象
					URL url = new URL(picUrl);
					InputStream is = url.openStream();
					byte[] buff = new byte[1024];
					while (true) {
						int readed = is.read(buff);
						if (readed == -1) {
							break;
						}
						byte[] temp = new byte[readed];
						System.arraycopy(buff, 0, temp, 0, readed);
						// 写入文件
						os.write(temp);
					}
					System.out.println("图片: " + file.getAbsolutePath());
					is.close();
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
		download.start();
	}

}
