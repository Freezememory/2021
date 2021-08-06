package com.wanglj.exercise.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Wanglj
 * @date 2021/8/6 9:34
 */
@Slf4j
public class CrawlerUtils {


    //获取响应体
    public String GetHttp(String url, int mark) {
        // 获取一页带音乐id的json数据
        StringBuffer body = new StringBuffer();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet HttpGet = new HttpGet(url);
        if (mark == 1) {
            // 设置请求头
            HttpGet.setHeader("csrf", "AOPHIP5DF2M");
            HttpGet.setHeader("Cookie",
                    "_ga=GA1.2.207336405.1578105222; _gid=GA1.2.1290422414.1578105222; Hm_lvt_cdb524f42f0ce19b169a8071123a4797=1578105222,1578105233,1578123281; Hm_lpvt_cdb524f42f0ce19b169a8071123a4797=1578123667; _gat=1; kw_token=AOPHIP5DF2M");
        }
        CloseableHttpResponse response;
        try {
            response = httpClient.execute(HttpGet);
            InputStream inputStream = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String s = "";
            while ((s = reader.readLine()) != null) {
                body.append(s);
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return body.toString();
    }


    /**
     * 建立连接
     *
     * @param url         抓取url
     * @param charsetName 编码类型
     * @return String
     * @throws IOException
     */
    public static String connect(String url, String charsetName) throws IOException {
        BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(connManager)
                .useSystemProperties()
                .build();
        String content = "";
        try {
            HttpGet httpget = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(5000)
                    .setConnectTimeout(50000)
                    .setConnectionRequestTimeout(50000)
                    .build();
            httpget.setConfig(requestConfig);
            httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpget.setHeader("Accept-Encoding", "gzip,deflate,sdch");
            httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpget.setHeader("Connection", "keep-alive");
            httpget.setHeader("Upgrade-Insecure-Requests", "1");
            httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
            httpget.setHeader("cache-control", "max-age=0");
            httpget.setHeader("csrf", "NIKH2WAJ1U");
            httpget.setHeader("cookie", "_ga=GA1.2.269694571.1628157050; _gid=GA1.2.614248.1628157050; Hm_lvt_cdb524f42f0ce19b169a8071123a4797=1628157050,1628213105; Hm_lpvt_cdb524f42f0ce19b169a8071123a4797=1628221587; kw_token=NIKH2WAJ1U");//小红书
            // httpget.setHeader("cookie", "Hm_lvt_01dd6a7c493607e115255b7e72de5f40=1626749455; MSCookieKey=ba77a49cff5aff867a58c1b15c80f353.; Hm_lpvt_01dd6a7c493607e115255b7e72de5f40=1626749655");//美食杰
            CloseableHttpResponse response = httpclient.execute(httpget);
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charsetName));
                StringBuffer sbf = new StringBuffer();
                String line = null;
                while ((line = br.readLine()) != null) {
                    sbf.append(line).append("\n");
                }
                br.close();
                content = sbf.toString();
            } else {
                content = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
        return content;
    }


    public String doPost(String url, Map<String, String> map, String charset) {
        BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setConnectionManager(connManager)
                .useSystemProperties()
                .build();
        String result = null;
        try {
            // httpClient = new SSLClient();
            HttpPost httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpclient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    /**
     * post请求（用于请求json格式的参数）
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, String params) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            } else {
                log.error("请求返回:" + state + "(" + url + ")");
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
