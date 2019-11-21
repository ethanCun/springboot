package com.example.httpclient.demohttpclient.cpntroller;

import com.alibaba.fastjson.JSON;
import com.example.httpclient.demohttpclient.entity.User;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SendController {

    /**
     * 不带参数的请求
     */
    @GetMapping(value = "/sendNoParam")
    public void sendNoParam(){

        //获取httpclient客户端
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();

        //创建get请求
        HttpGet httpGet = new HttpGet("http://localhost:8080/get");

        //响应模型
        CloseableHttpResponse closeableHttpResponse = null;

        try {

            //客户端发起get请求
            closeableHttpResponse = closeableHttpClient.execute(httpGet);

            //从响应模型中获取响应实体
            HttpEntity httpEntity = closeableHttpResponse.getEntity();

            if (httpEntity != null){

                System.out.println("响应状态:" + closeableHttpResponse.getStatusLine());
                System.out.println("响应内容:" + EntityUtils.toString(httpEntity));
            }

        }catch (Exception e){

        }finally {

           try {

               //释放资源
               if (closeableHttpClient != null){
                   closeableHttpClient.close();
               }
               if (closeableHttpResponse != null){
                   closeableHttpResponse.close();
               }
           }catch (Exception e){

           }

        }
    }


    /**
     * 带参数的请求 参数使用StringBuffer拼接
     * @param myname
     * @param myage
     */
    @GetMapping(value = "/sendWithParam")
    public void sendWithParam(String myname, String myage){

        //请求客户端
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();

        //请求参数 param拼接
        StringBuffer param = new StringBuffer();
        param.append("myname="+myname);
        param.append("&myage="+myage);

        //请求方式
        HttpGet httpGet = new HttpGet("http://localhost:8080/getWithParam?" + param);

        //配置httpGet请求的信息
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000) //连接超时时间
                .setConnectionRequestTimeout(5000) //请求超时时间
                .setSocketTimeout(5000) //socket读写超时时间
                .setRedirectsEnabled(true) //是否允许重定向
                .build();

        httpGet.setConfig(requestConfig);

        //响应模型
        CloseableHttpResponse response = null;

        try {

            //发起请求
            response = closeableHttpClient.execute(httpGet);

            //请求内容
            HttpEntity httpEntity = response.getEntity();

            System.out.println("请求状态: " + response.getStatusLine());
            System.out.println("请求内容: " + EntityUtils.toString(httpEntity));

        }catch (Exception e){

        }finally {

            try {

                if (closeableHttpClient != null){
                    closeableHttpClient.close();
                }
                if (response != null){
                    response.close();
                }
            }catch (Exception e){

            }

        }
    }

    /**
     * 带参数的请求 使用URIBuilder + List<NameValuePair>构建
     * @param myname
     * @param myage
     */
    @GetMapping(value = "sendWithParamUri")
    public void sendWithParamUri(String myname, String myage){

        //客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //请求参数：
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("myname", myname));
        params.add(new BasicNameValuePair("myage", myage));

        //构建uri
        URI uri = null;

        try {

            uri = new URIBuilder().setScheme("http")
                    .setHost("localhost").setPort(8080).setPath("getWithParam")
                    .setParameters(params).build();


        }catch (Exception e){

        }

        //请求方式
        HttpGet httpGet = new HttpGet(uri);

        //响应模型
        CloseableHttpResponse response = null;

        try {

            //发起请求
            response = httpClient.execute(httpGet);

            //响应内容
            HttpEntity httpEntity = response.getEntity();

            System.out.println("响应状态: " + response.getStatusLine());
            System.out.println("响应内容: " + EntityUtils.toString(httpEntity));

        }catch (Exception e){

        }finally {

            try {

                //释放资源
                if (httpClient != null){
                    httpClient.close();
                }
                if (response != null){
                    response.close();
                }
            }catch (Exception e){

            }
        }
    }


    /**
     * httpclient不带参数的post请求
     */
    @GetMapping(value = "/sendWithPostNoParam")
    public void sendWithPostNoParam(){

        //请求客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //请求方式
        HttpPost httpPost = new HttpPost("http://localhost:8080/postWithNoParam");

        //请求配置
        RequestConfig requestConfig = RequestConfig.custom()
                .setRedirectsEnabled(false)
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();

        httpPost.setConfig(requestConfig);

        //响应模型
        CloseableHttpResponse response = null;

        try {

            response = httpClient.execute(httpPost);

            HttpEntity httpEntity = response.getEntity();

            System.out.println("请求状态: " + response.getStatusLine());
            System.out.println("请求内容: " + EntityUtils.toString(httpEntity));

        }catch (Exception e){


        }finally {

            try {

                if (httpClient != null){
                    httpClient.close();
                }
                if (response != null){
                    response.close();
                }
            }catch (Exception e){

            }
        }
    }

    /**
     * httpclient带普通参数的post请求
     * @param name
     * @param age
     */
    @GetMapping(value = "/sendWithPostParam")
    public void sendWithPostParam(String name, String age){

        //客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //请求参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("age", age));

        //uri
        URI uri = null;

        try {

            uri = new URIBuilder().setScheme("http").setHost("localhost")
                    .setPort(8080).setPath("postWithParam")
                    .setParameters(params).build();
        }catch (Exception e){

        }

        //请求方式
        HttpPost httpPost = new HttpPost(uri);

        //响应模型
        CloseableHttpResponse response = null;

        try {

            response = httpClient.execute(httpPost);

            //响应内容
            HttpEntity httpEntity = response.getEntity();

            System.out.println("响应状态: " + response.getStatusLine());
            System.out.println("响应内容: " + EntityUtils.toString(httpEntity));

        }catch (Exception e){

        }finally {

            try {

                if (httpClient != null){
                    httpClient.close();
                }
                if (response != null){
                    response = null;
                }
            }catch (Exception e){

            }
        }
    }


    /**
     * httpclient的form post表单请求
     * Content-Type:application/x-www-form-urlencoded
     * @param name
     * @param age
     */
    @GetMapping(value = "/sendWithPostForm")
    public void sendWithPostForm(String name, String age){

        //客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //请求form表单
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("age", age));

        //表单请求
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, StandardCharsets.UTF_8);

        //请求方式
        HttpPost httpPost = new HttpPost("http://localhost:8080/postWithParam");
        httpPost.setEntity(formEntity);

        //设置请求头为表单发送方式
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

        //响应模型
        CloseableHttpResponse response = null;

        try {

            response = httpClient.execute(httpPost);

            //响应内容
            HttpEntity httpEntity = response.getEntity();

            System.out.println("表单请求响应状态: " + response.getStatusLine());
            System.out.println("响应内容: " + EntityUtils.toString(httpEntity, StandardCharsets.UTF_8));

        }catch (Exception e){

        }finally {
            try {

                if (httpClient != null){
                    httpClient.close();
                }
                if (response != null){
                    response.close();
                }
            }catch (Exception e){

            }
        }
    }

    /**
     * httpclient发送带有对象和普通参数的post请求
     * Content-Type:application/json;charset=utf8
     * @param user
     * @param name
     * @param age
     */
    @PostMapping(value = "/sendWithObjectParam")
    public void sendWithObjectParam(@RequestBody User user, String name, String age){

        System.out.println("send: user = " + user + " name = " + name
        + " age = " + age);

        //请求客户端
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        //普通参数 可以使用拼接的方式拼接成StringBuffer, 也可以使用URIBuilder构建
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("age", age));

        URI uri = null;

        try {

            uri = new URIBuilder().setScheme("http").setHost("localhost")
                    .setPort(8080).setPath("postWithObjectAndParam")
                    .setParameters(params).build();
        }catch (Exception e){

        }

        //请求方式
        HttpPost httpPost = new HttpPost(uri);

        //对象参数
        String userJsonStr = JSON.toJSONString(user);
        StringEntity stringEntity = new StringEntity(userJsonStr, "UTF-8");
        httpPost.setEntity(stringEntity);

        //带有对象的请求需要设置请求头Content-Type为application/json
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        //响应模型
        CloseableHttpResponse response = null;

        try {

            //发起请求
            response = httpClient.execute(httpPost);

            //响应内容
            HttpEntity httpEntity = response.getEntity();

            System.out.println("响应状态: " + response.getStatusLine());
            //设置Charset为UTF8解决乱码现象
            System.out.println("响应内容: " + EntityUtils.toString(httpEntity, StandardCharsets.UTF_8));
        }catch (Exception e){

        }finally {

            try {

                if (httpClient != null){
                    httpClient.close();
                }
                if (response != null){
                    response.close();
                }
            }catch (Exception e){

            }
        }
    }


    /***
     * httpclient发送文件file:
     *  使用multipartEntityBuilder来添加二进制文件 以及普通text文本， 再转化为HttpEntity传输
     */
    @GetMapping(value = "/sendFile")
    public void sendFile(){

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("http://localhost:8080/file");

        CloseableHttpResponse response = null;

        try {

            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

            //第一个文件
            String filekeys = "files";
            File file1 = new File("/Users/macofethan/Desktop/2.jpeg");
            multipartEntityBuilder.addBinaryBody(filekeys, file1, ContentType.MULTIPART_FORM_DATA, URLEncoder.encode(file1.getName(), "utf-8"));

            //第二个文件 同一个filekeys 接收端用一个数组接受
            File file2 = new File("/Users/macofethan/Desktop/1.jpg");
            multipartEntityBuilder.addBinaryBody(filekeys, file2, ContentType.DEFAULT_BINARY, URLEncoder.encode(file2.getName(), "utf-8"));

            //其他参数
            ContentType contentType = ContentType.create("text/plain", Charset.forName("UTF-8"));
            multipartEntityBuilder.addTextBody("name", "zhangsan", contentType);
            multipartEntityBuilder.addTextBody("age", "100", contentType);

            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);

            //响应模型
            response = httpClient.execute(httpPost);

            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态: " + response.getStatusLine());

            if (responseEntity != null){

                System.out.println("响应内容: " + EntityUtils.toString(responseEntity, StandardCharsets.UTF_8));
            }


        }catch (Exception e){


        }finally {

            try {

                if (httpClient != null){
                    httpClient.close();
                }
                if (response != null){
                    response.close();
                }
            }catch (Exception e){

            }
        }
    }


    /**
     * httpclient发送二进制流
     */
    @GetMapping(value = "/sendIs")
    public void sendInputStream(String name){

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("name", name));

        URI uri = null;

        try {

            uri = new URIBuilder().setScheme("http").setHost("localhost")
                    .setPort(8080).setPath("is").setParameters(params).build();
        }catch (Exception e){

        }

        HttpPost httpPost = new HttpPost(uri);

        CloseableHttpResponse response = null;

        try {

            File file = new File("/Users/macofethan/Desktop/1.mp4");

            InputStream is = new FileInputStream(file);

            /**
             * 输入流 传输的长度最多1m 传输的ContentType
             */
            InputStreamEntity inputStreamEntity = new InputStreamEntity(is, 1024*1024, ContentType.DEFAULT_BINARY);

            httpPost.setEntity(inputStreamEntity);

            response = httpClient.execute(httpPost);

            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态： " + response.getStatusLine());
            System.out.println("响应内容: " + EntityUtils.toString(responseEntity));

        }catch (Exception e){

        }finally {
            try {
                if (httpClient != null){
                    httpClient.close();
                }
                if (response != null){
                    response.close();
                }
            }catch (Exception e){

            }
        }
    }
}

