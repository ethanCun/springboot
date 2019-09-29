package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.User;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HttpClientController {

    /**
     * 不带参数的get请求
     * */
    @RequestMapping(value = "/get")
    public void testGet() throws IOException {

        //获得Http客户端(可以理解为:你得先有一个浏览器;
        // 注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient hc = HttpClientBuilder.create().build();

        //创建啊get请求
        HttpGet httpGet = new HttpGet("http://localhost:8811/get");

        //响应模型
        CloseableHttpResponse response = null;

        try {

            response = hc.execute(httpGet);

            //从响应模型中获取实体模型
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态：" + response.getStatusLine());

            if (responseEntity != null){

                System.out.println("响应长度:" + responseEntity.getContentLength());
                System.out.println("响应内容:" + EntityUtils.toString(responseEntity));
            }

        }catch (ParseException e){

            e.printStackTrace();

        }finally {

            try {

                //释放资源
                if (hc != null){
                    hc.close();
                }
                if (response != null){
                    response.close();
                }

            }catch (IOException e){

                e.printStackTrace();
            }
        }

    }


    /**
     * 带简单参数的get请求
     * */
    @RequestMapping(value = "testGetWithSimpleParam")
    public void testGetWithSimpleParam(String name){

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //配置参数
        StringBuffer params = new StringBuffer();

        try {

            params.append("name=" + URLEncoder.encode(name, "utf-8"));

        }catch (UnsupportedEncodingException e){

            e.printStackTrace();
        }

        //创建get请求
        HttpGet httpGet = new HttpGet("http://localhost:8811/getWithSimpleParam?" + params);

        //客户端发起get请求
        CloseableHttpResponse response = null;

        try {

//            //配置信息
//            RequestConfig requestConfig = RequestConfig.custom()
//                    //设置链接超时时间
//            .setConnectTimeout(5000)
//                    //设置请求超时时间
//            .setConnectionRequestTimeout(5000)
//                    //socket读写超时时间
//            .setSocketTimeout(5000)
//                    //设置允许重定向
//            .setRedirectsEnabled(true).build();
//
//
//            //配置信息加入请求
//            httpGet.setConfig(requestConfig);

            //执行请求
            response = httpClient.execute(httpGet);

            //获取响应实体
            HttpEntity httpEntity = response.getEntity();

            if (httpClient != null){

                System.out.println("响应长度：" + httpEntity.getContentLength());
                System.out.println("响应内容:" + EntityUtils.toString(httpEntity));
            }

        }catch (Exception e){

            e.printStackTrace();
        }

    }

    /**
     * 带实体参数的get请求：使用普通拼接请求地址
     * */
    @RequestMapping(value = "/testGetWithEntityParam")
    public void testGetWithEntityParam(String name, String age){


        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        StringBuffer params = new StringBuffer();

        try {

            params.append("name=" + URLEncoder.encode(name, "utf-8"));
            params.append("&");
            params.append("age=" + URLEncoder.encode(age, "utf-8"));

        }catch (UnsupportedEncodingException e){

            e.printStackTrace();
        }

        CloseableHttpResponse response = null;

        try {

            HttpGet httpGet = new HttpGet("http://localhost:8811/getWithEntityParam?" + params);

            response = httpClient.execute(httpGet);

            HttpEntity httpEntity = response.getEntity();

            System.out.println("响应状态:" + response.getStatusLine());
            System.out.println("响应长度:" + httpEntity.getContentLength());
            System.out.println("响应内容:" + EntityUtils.toString(httpEntity));

        }catch (Exception e){

            e.printStackTrace();
        }
    }


    /**
     * 带实体参数的get请求方式2：使用URIBuilder拼接请求地址
     * */
    @RequestMapping(value = "/testGetWithEntityParam2")
    public void testGetWithEntityParam2(String name, String age){

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //参数
        URI uri = null;

        try {

            //将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("age", age));

            //设置uri信息,并将参数集合放入uri;
            //注:这里也支持一个键值对一个键值对地往里面放
            // setParameter(String key, String value)
            uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("localhost")
                    .setPort(8811)
                    .setPath("/getWithEntityParam")
                    .setParameters(params)
                    .build();

        }catch (URISyntaxException e){

            e.printStackTrace();
        }

        //创建get请求
        HttpGet httpGet = new HttpGet(uri);

        CloseableHttpResponse response = null;

        try {

            //配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000)
                    .setConnectionRequestTimeout(5000)
                    .setSocketTimeout(5000)
                    .setRedirectsEnabled(true).build();

            httpGet.setConfig(requestConfig);

            response = httpClient.execute(httpGet);

            HttpEntity httpEntity = response.getEntity();

            System.out.println("响应内容:" + EntityUtils.toString(httpEntity));

        }catch (Exception e){

            e.printStackTrace();
        }
    }


    /**
     * 不带参数的post请求
     * */
    @RequestMapping(value = "/testSimplePost")
    public void testSimplePost(){

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        URI uri = null;

        try {

            uri = new URIBuilder().setScheme("http")
                    .setHost("localhost")
                    .setPort(8811)
                    .setPath("/post")
                    .build();

        }catch (Exception e){

            e.printStackTrace();
        }


        //方式1： 使用URIBuilder构建
//        HttpPost httpPost = new HttpPost(uri);

        //方式2: 直接拼接
        HttpPost httpPost = new HttpPost("http://localhost:8811/post");

        CloseableHttpResponse response = null;

        try {

            response = httpClient.execute(httpPost);

            HttpEntity httpEntity = response.getEntity();

            System.out.println("post响应内容:" + EntityUtils.toString(httpEntity));

        }catch (Exception e){

            e.printStackTrace();
        }finally {

            try {

                if (httpClient != null){
                    httpClient.close();
                }
                if (response != null){
                    response.close();
                }
            }catch (IOException ee){
                ee.printStackTrace();
            }
        }
    }


    /**
     * 带有实体参数的post请求
     * */
    @RequestMapping(value = "/testEntityPost")
    public void testEntityPost(User user){

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        URI uri = null;

        try {

            uri = new URIBuilder().setScheme("http").setHost("localhost")
                    .setPort(8811).setPath("/postWithEntity").build();
        }catch (URISyntaxException e){

        }

        HttpPost httpPost = new HttpPost(uri);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        //参数
        String jsonString = JSON.toJSONString(user);

        //设置utf-8编码
        StringEntity entity = new StringEntity(jsonString, StandardCharsets.UTF_8);

        httpPost.setEntity(entity);

        CloseableHttpResponse response = null;

        try {

            response = httpClient.execute(httpPost);

            HttpEntity responseEntity = response.getEntity();

            System.out.println("post带实体参数响应内容:" + EntityUtils.toString(responseEntity));

        }catch (IOException e){

            e.printStackTrace();
        }
    }
}
