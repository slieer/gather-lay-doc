package com.skyworthbox.parserHtml;

import java.util.HashMap;
import java.util.Map;

import com.roxstudio.utils.CUrl;

public class DocParserStyle {

	//curl 'http://wenshu.court.gov.cn/List/ListContent' 
	//-H 'Cookie: _gscu_2116842793=55511477vlo7bn94; _gscbrs_2116842793=1; Hm_lvt_d2caefee2de09b8a6ea438d74fd98db2=1555511477,1555514271; ASP.NET_SessionId=s3vnc4mbellubhgekina1gce; Hm_lpvt_d2caefee2de09b8a6ea438d74fd98db2=1555594554; _gscs_2116842793=t55594553frka2v94|pv:1; wzws_cid=f077db5da62f0830c91b1982f2e28f0797c5ae8f5048248aafa17d8f7054b9f0b4ff46208998c9cdbe7a9e4a4a481083a9d63ea6158f2bef4e20ce594e7a7f36; vjkl5=13627e79495ffad30e601cb183704fcc5a37f29d' 
	//-H 'Origin: http://wenshu.court.gov.cn' 
	//-H 'Accept-Encoding: gzip, deflate' 
	//-H 'Accept-Language: zh-CN,zh;q=0.9' 
	//-H 'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36' 
	//-H 'Content-Type: application/x-www-form-urlencoded; charset=UTF-8' -H 'Accept: */*' 
	//-H 'Referer: http://wenshu.court.gov.cn/list/list/?sorttype=1&number=0.5191832203422626&guid=8a744b71-75c1-e1158bfd-5c764e544ebd&conditions=searchWord+2+AJLX++%E6%A1%88%E4%BB%B6%E7%B1%BB%E5%9E%8B:%E6%B0%91%E4%BA%8B%E6%A1%88%E4%BB%B6&conditions=searchWord+%E6%B0%91%E4%BA%8B%E6%A1%88%E7%94%B1+++%E4%B8%80%E7%BA%A7%E6%A1%88%E7%94%B1:%E6%B0%91%E4%BA%8B%E6%A1%88%E7%94%B1' 
	//-H 'X-Requested-With: XMLHttpRequest' 
	//-H 'Connection: keep-alive' 
	//--data 'Param=%E6%A1%88%E4%BB%B6%E7%B1%BB%E5%9E%8B%3A%E6%B0%91%E4%BA%8B%E6%A1%88%E4%BB%B6%2C%E4%B8%80%E7%BA%A7%E6%A1%88%E7%94%B1%3A%E6%B0%91%E4%BA%8B%E6%A1%88%E7%94%B1&Index=1&Page=10&Order=%E6%B3%95%E9%99%A2%E5%B1%82%E7%BA%A7&Direction=asc&vl5x=829e2c87392d0663761bbe21&number=0.51&guid=232f3775-29d4-4e537d8e-e21f317a0a08' 
	//--compressed	 
	public static void pageReq() {
		Map<String, String> headers = new HashMap<>();
		
		headers.put("Cookie", "_gscu_2116842793=55511477vlo7bn94; _gscbrs_2116842793=1; Hm_lvt_d2caefee2de09b8a6ea438d74fd98db2=1555511477,1555514271; ASP.NET_SessionId=s3vnc4mbellubhgekina1gce; Hm_lpvt_d2caefee2de09b8a6ea438d74fd98db2=1555594554; _gscs_2116842793=t55594553frka2v94|pv:1; wzws_cid=f077db5da62f0830c91b1982f2e28f0797c5ae8f5048248aafa17d8f7054b9f0b4ff46208998c9cdbe7a9e4a4a481083a9d63ea6158f2bef4e20ce594e7a7f36; vjkl5=13627e79495ffad30e601cb183704fcc5a37f29d");
		headers.put("Origin", "http://wenshu.court.gov.cn");
		headers.put("Accept-Encoding", "gzip, deflate");
		headers.put("Accept-Language", "zh-CN,zh;q=0.9");
		headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
		headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8' -H 'Accept: */*");
		headers.put("Referer", "http://wenshu.court.gov.cn/list/list/?sorttype=1&number=0.5191832203422626&guid=8a744b71-75c1-e1158bfd-5c764e544ebd&conditions=searchWord+2+AJLX++%E6%A1%88%E4%BB%B6%E7%B1%BB%E5%9E%8B:%E6%B0%91%E4%BA%8B%E6%A1%88%E4%BB%B6&conditions=searchWord+%E6%B0%91%E4%BA%8B%E6%A1%88%E7%94%B1+++%E4%B8%80%E7%BA%A7%E6%A1%88%E7%94%B1:%E6%B0%91%E4%BA%8B%E6%A1%88%E7%94%B1");
		headers.put("X-Requested-With", "XMLHttpRequest");
		headers.put("Connection", "keep-alive");
		String url = "http://wenshu.court.gov.cn/List/ListContent";
        String param = "案件类型:民事案件,一级案由:民事案由";
		String order = "法院层级";
		CUrl curl = new CUrl(url)
        		.headers(headers)
        		.data("Param=" + param)
        		.data("Index=1")
        		.data("Page=10")
        		.data("Order" + order)
        		.data("Direction=asc")
        		.data("vl5x=a4a2db73f33e698da08b8c9d")
        		.data("number=0.51")
        		.data("guid=a21357e7-0f6a-ab5b33bc-bd64714d62e4");        		        		
        String result = curl.exec("UTF-8");
        
        System.out.println(result);
    }
	
//	POST http://wenshu.court.gov.cn/CreateContentJS/CreateListDocZip.aspx?action=1 HTTP/1.1
//		Host: wenshu.court.gov.cn
//		Connection: keep-alive
//		Content-Length: 743
//		Cache-Control: max-age=0
//		Origin: http://wenshu.court.gov.cn
//		Upgrade-Insecure-Requests: 1
//		Content-Type: application/x-www-form-urlencoded
//		User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36
//		Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3
//		Referer: http://wenshu.court.gov.cn/list/list/?sorttype=1&number=0.5191832203422626&guid=8a744b71-75c1-e1158bfd-5c764e544ebd&conditions=searchWord+2+AJLX++%E6%A1%88%E4%BB%B6%E7%B1%BB%E5%9E%8B:%E6%B0%91%E4%BA%8B%E6%A1%88%E4%BB%B6&conditions=searchWord+%E6%B0%91%E4%BA%8B%E6%A1%88%E7%94%B1+++%E4%B8%80%E7%BA%A7%E6%A1%88%E7%94%B1:%E6%B0%91%E4%BA%8B%E6%A1%88%E7%94%B1
//		Accept-Encoding: gzip, deflate
//		Accept-Language: zh-CN,zh;q=0.9
//		Cookie: _gscu_2116842793=55511477vlo7bn94; _gscbrs_2116842793=1; Hm_lvt_d2caefee2de09b8a6ea438d74fd98db2=1555511477,1555514271; wzws_cid=e482fdc3395ebce1715b2500621cd987fa6f1dda951d01d75f687344a1652ec44738d6b9dc3e5b4a7ab514addee9fc272309868991bd170f784086b78c96c257; ASP.NET_SessionId=s3vnc4mbellubhgekina1gce; vjkl5=68fddad1195ffe42b650115187061be8a5e78291; _gscs_2116842793=55514270ivs6ll94|pv:3; Hm_lpvt_d2caefee2de09b8a6ea438d74fd98db2=1555514585
//
//		conditions=%25E6%25A1%2588%25E4%25BB%25B6%25E7%25B1%25BB%25E5%259E%258B%25E4%25B8%25BA%25E6%25B0%2591%25E4%25BA%258B%25E6%25A1%2588%25E4%25BB%25B6%25E4%25B8%2594%25E4%25B8%2580%25E7%25BA%25A7%25E6%25A1%2588%25E7%2594%25B1%25E4%25B8%25BA%25E6%25B0%2591%25E4%25BA%258B%25E6%25A1%2588%25E7%2594%25B1&docIds=d461337d-1e9d-475d-8c76-01e2e3a5c90e%7C%E5%8E%A6%E9%97%A8%E8%81%9A%E4%BA%BF%E7%94%B5%E6%B0%94%E8%AE%BE%E5%A4%87%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%E4%B8%8E%E5%BA%93%E6%9F%8F%E7%94%B5%E6%B0%94%EF%BC%88%E4%B8%8A%E6%B5%B7%EF%BC%89%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8%E4%B8%80%E8%88%AC%E4%B9%B0%E5%8D%96%E5%90%88%E5%90%8C%E7%BA%A0%E7%BA%B7%E7%94%B3%E8%AF%B7%E5%86%8D%E5%AE%A1%E6%B0%91%E4%BA%8B%E8%A3%81%E5%AE%9A%E4%B9%A6%7C2014-12-17&keyCode=
	
	public void downDoc() {
		
	}
	
	public static void main(String[] args) {
		pageReq();
	}
}
