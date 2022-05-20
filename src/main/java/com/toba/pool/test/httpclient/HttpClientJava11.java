package com.toba.pool.test.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.net.URI;

import java.util.Map;

public class HttpClientJava11 {

    public static void main(String[] args) throws Exception {
        get();
    }
    public static void get() throws Exception {
        try {
            BasicCookieStore cookieStore = new BasicCookieStore();
            BasicClientCookie cookie = new BasicClientCookie("test", "test1234");
            cookie.setDomain("localhost");
            cookie.setPath("/");
            cookieStore.addCookie(cookie);
            HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build(); // HttpClient 생성
            HttpGet getRequest = new HttpGet("http://localhost:8090/gcoo"); //GET 메소드 URL 생성
            Object RestTestCommon;
            //getRequest.addHeader("x-api-key", RestTestCommon.API_KEY); //KEY 입력


            for (int ii = 0; ii < 1000; ii++) {
                getRequest.setHeader("Cookie", "JSESSIONID=1234-"+ii);
                HttpResponse response = client.execute(getRequest);

                //Response 출력
                if (response.getStatusLine().getStatusCode() == 200) {
                    ResponseHandler<String> handler = new BasicResponseHandler();
                    String body = handler.handleResponse(response);
                    System.out.println(body);
                } else {
                    System.out.println("response is error : " + response.getStatusLine().getStatusCode());
                }
            }

        } catch (Exception e){
            System.err.println(e.toString());
        }

    }
}