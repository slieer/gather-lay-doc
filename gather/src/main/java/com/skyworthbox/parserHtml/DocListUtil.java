package com.skyworthbox.parserHtml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.script.ScriptException;

import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;

public class DocListUtil {
	static String url = "http://wenshu.court.gov.cn/List/ListContent";
	static String defaultCharset = "utf-8";
	static String param = "案件类型:民事案件,一级案由:民事案由";
	static String order = "法院层级";		
	
	static String vjkl5Val = "9b4e95ffed3e03015a1896b52668b7e053d45341";
	static String cookie = "_gscu_2116842793=55511477vlo7bn94; _gscbrs_2116842793=1; Hm_lvt_d2caefee2de09b8a6ea438d74fd98db2=1555511477,1555514271; ASP.NET_SessionId=s3vnc4mbellubhgekina1gce; Hm_lpvt_d2caefee2de09b8a6ea438d74fd98db2=1555594554; _gscs_2116842793=t55594553frka2v94|pv:1; wzws_cid=f077db5da62f0830c91b1982f2e28f0797c5ae8f5048248aafa17d8f7054b9f0b4ff46208998c9cdbe7a9e4a4a481083a9d63ea6158f2bef4e20ce594e7a7f36; vjkl5=" + vjkl5Val;
	public static void main(String[] args) {
		try {
			String result = send();
			System.out.println(result);
			
		} catch (ParseException | IOException |NoSuchMethodException | ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public static String send() throws ParseException, IOException, NoSuchMethodException, ScriptException {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("Param", param);
		map.put("Index", "1");
		map.put("Page", "10");
		map.put("Order", order);
		map.put("Direction", "asc");
		map.put("vl5x", KeyUtil.getKey(vjkl5Val));
		map.put("number","0.51");
		map.put("guid", UUID.randomUUID().toString());    
		//"a21357e7-0f6a-ab5b33bc-bd64714d62e4"
		
		// 创建httpclient对象
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建post方式请求对象
		HttpPost httpPost = new HttpPost(url);
		RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		httpPost.setConfig(defaultConfig);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
		System.out.println("params:" + json);
		// 装填参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (map != null) {
			for (Entry<String, String> entry : map.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		// 设置参数到请求对象中
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, defaultCharset));
		
		// 设置header信息
		// 指定报文头【Content-type】、【User-Agent】
		httpPost.setHeaders(getHeaders());

		// 执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);
		// 获取结果实体
		String body = "";
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			// 按指定编码转换结果实体为String类型
			body = EntityUtils.toString(entity, defaultCharset);
		}
		
		//System.out.println(response.getStatusLine());
		EntityUtils.consume(entity);
		// 释放链接
		response.close();
		return body;
	}

	static Header[] getHeaders() {
		
		final Map<String, String> cookieMap = Splitter.on("; ").withKeyValueSeparator("=").split(cookie);
		String vjkl5Val = cookieMap.get("vjkl5");
		System.out.println(vjkl5Val);
		
		List<Header> list = Arrays.asList(new BasicHeader("Cookie",
				cookie),
				new BasicHeader("Origin", "http://wenshu.court.gov.cn"),
				new BasicHeader("Accept-Encoding", "gzip, deflate"),
				new BasicHeader("Accept-Language", "zh-CN,zh;q=0.9"),
				new BasicHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36"),
				new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8' -H 'Accept: */*"),
				new BasicHeader("Referer",
						"http://wenshu.court.gov.cn/list/list/?sorttype=1&number=0.5191832203422626&guid=8a744b71-75c1-e1158bfd-5c764e544ebd&conditions=searchWord+2+AJLX++%E6%A1%88%E4%BB%B6%E7%B1%BB%E5%9E%8B:%E6%B0%91%E4%BA%8B%E6%A1%88%E4%BB%B6&conditions=searchWord+%E6%B0%91%E4%BA%8B%E6%A1%88%E7%94%B1+++%E4%B8%80%E7%BA%A7%E6%A1%88%E7%94%B1:%E6%B0%91%E4%BA%8B%E6%A1%88%E7%94%B1"),
				new BasicHeader("X-Requested-With", "XMLHttpRequest"), 
				new BasicHeader("Connection", "keep-alive")

		);

		Header[] arr = new Header[list.size()];
		list.toArray(arr);

		return arr;
	}
}
