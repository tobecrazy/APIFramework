package main.java.cn.dbyl.tests;

import java.net.URI;
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

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import main.java.cn.dbyl.APIUtils.JsonUtils;
import main.java.cn.dbyl.beans.ZhihuAPIBean;

public class QuickStart {

	public static void main(String[] args) throws Exception {

		ZhihuAPIBean zhihu = null;
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		;
		try {
			URI uri = new URIBuilder().setScheme("http").setHost("news-at.zhihu.com")
					.setPath("/api/7/prefetch-launch-images/1080*1920")
					// .setParameter("q", "httpclient")
					// .setParameter("btnG", "Google Search")
					// .setParameter("aq", "f")
					// .setParameter("oq", "")
					.build();
			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Content-Type", "application/json; charset=UTF-8");
			System.out.println(httpGet.getURI());
			CloseableHttpResponse response1 = httpclient.execute(httpGet);

			System.out.println(response1.getStatusLine());
			HttpEntity entity = response1.getEntity();
			try {
				if (entity != null) {
					long len = entity.getContentLength();
					if (len != -1 && len < 2048) {
						String str = EntityUtils.toString(entity);
						System.out.println(str);
						zhihu=(ZhihuAPIBean) JsonUtils.getInstance().parseStringToBean(str, ZhihuAPIBean.class);
					} else {
						// Stream content out
						
					}
				}
			} finally {
				response1.close();
			}
			EntityUtils.consume(entity);
			System.out.println(zhihu.getCreatives().get(0).getStartTime());

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

}
