package com.skyworthbox.parserHtml;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.openqa.selenium.json.Json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;

public class ParamUtil {
	public static void main(String[] args) {
		try {
			getVjkl5();
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
//	curl "http://wenshu.court.gov.cn/List/List?sorttype=1&conditions=searchWord+1+AJLX++"%"E6"%"A1"%"88"%"E4"%"BB"%"B6"%"E7"%"B1"%"BB"%"E5"%"9E"%"8B:"%"E5"%"88"%"91"%"E4"%"BA"%"8B"%"E6"%"A1"%"88"%"E4"%"BB"%"B6" 
//	-H "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0" 
//	-H "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8" 
//	-H "Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2" 
//	--compressed -H "Referer: http://wenshu.court.gov.cn/List/List?sorttype=1&conditions=searchWord+1+AJLX++"%"E6"%"A1"%"88"%"E4"%"BB"%"B6"%"E7"%"B1"%"BB"%"E5"%"9E"%"8B:"%"E5"%"88"%"91"%"E4"%"BA"%"8B"%"E6"%"A1"%"88"%"E4"%"BB"%"B6" 
//	-H "Connection: keep-alive" 
//	-H "Cookie: _gscu_2116842793=555949608pr4dc20; wzws_cid=853b59e3881a5451306c062f5ad62b7d19e0ff31eb4b5e429db9bd5aa19165225edb02a223892843d91770a055cb89f68b18619e2eeb54e08488b7dd12b8b7bd" 
//	-H "Upgrade-Insecure-Requests: 1"
	
	static String url = "http://wenshu.court.gov.cn/List/List?sorttype=1&conditions=searchWord+1+AJLX++案件类型:刑事案件";
	public static String getVjkl5() throws ParseException, IOException {		
		// 创建httpclient对象
		CloseableHttpClient client = HttpClients.createDefault();
		// 创建post方式请求对象
		HttpGet httpPost = new HttpGet(url);
		RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
		httpPost.setConfig(defaultConfig);

		System.out.println("请求地址：" + url);

		// 设置header信息
		// 指定报文头【Content-type】、【User-Agent】
		httpPost.setHeaders(getHeaders());

		// 执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);
		System.out.println(response.getStatusLine());
		
		String cooke = response.getHeaders("Set-Cookie")[0].getValue();
		System.out.println(cooke);
		
		Header[] headers = response.getAllHeaders();
		
		ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(headers);
        System.out.println(json);
		// 释放链接
		response.close();
		return "";
	}

	static Header[] getHeaders() {		
		String cookie = "_gscu_2116842793=555949608pr4dc20; wzws_cid=853b59e3881a5451306c062f5ad62b7d19e0ff31eb4b5e429db9bd5aa19165225edb02a223892843d91770a055cb89f68b18619e2eeb54e08488b7dd12b8b7bd";
		final Map<String, String> cookieMap = Splitter.on("; ").withKeyValueSeparator("=").split(cookie);
		
		System.out.println(cookie);
		List<Header> list = Arrays.asList(new BasicHeader("Cookie",
				cookie),
				new BasicHeader("Accept-Encoding", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"),
				new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2"),
				new BasicHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36"),
				new BasicHeader("Connection", "keep-alive")
		);

		Header[] arr = new Header[list.size()];
		list.toArray(arr);

		return arr;
	}	
}
