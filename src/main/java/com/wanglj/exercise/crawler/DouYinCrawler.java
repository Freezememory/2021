package com.wanglj.exercise.crawler;


import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * @author Wanglj
 * @date 2021/7/22 10:49
 */
@Slf4j
public class DouYinCrawler {


    //视频链接(固定)
    private static final String videoPath = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=";

    //视频保存目录
    private static final String videoSavePath = "F:/抖音视频/";

    //分享链接（手动修改）
    private static String targetPath = "#笑动欢乐秀 #沈腾 第一次“死”没有经验？天使：填个调查问卷吧 https://v.douyin.com/JjHGuMc/ 复制此链接，打开【抖音短视频】，直接观看视频！";
    //private static String targetPath = "https://www.douyin.com/video/6945419683520711977?ug_source=sem_baidu&previous_page=others_homepage";


    public static void main(String[] args) throws IOException {
        Connection con = Jsoup.connect(filterUrl(targetPath));
        con.header("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");
        Connection.Response resp = con.method(Connection.Method.GET).execute();
        String videoUrl = videoPath + getItemId(resp.url().toString());
        String jsonStr = Jsoup.connect(videoUrl).ignoreContentType(true).execute().body();
        JSONObject json = JSONObject.parseObject(jsonStr);
        String videoAddress = json.getJSONArray("item_list").getJSONObject(0).getJSONObject("video").getJSONObject("play_addr").getJSONArray("url_list").get(0).toString();
        String title = json.getJSONArray("item_list").getJSONObject(0).getJSONObject("share_info").getString("share_title");
        videoAddress = videoAddress.replaceAll("playwm", "play");

        HashMap headers = MapUtil.newHashMap();
        headers.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 12_1_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16D57 Version/12.0 Safari/604.1");
        String finalVideoAddress = HttpUtil.createGet(videoAddress).addHeaders(headers).execute().header("Location");

        //注:打印获取的链接
        System.out.println("-----抖音去水印链接-----\n" + finalVideoAddress);
        //下载无水印视频到本地
        //String title = "test";
        downVideo(finalVideoAddress, title);
    }

/*    public static void main(String[] args) {
       // String url = "https://v26-web.douyinvod.com/3cedd6a814f733e4ffa9c4350f2883db/60f91ea5/video/tos/cn/tos-cn-ve-15/fad42cecbbe042baab11fe90f5e9ff30/?a=6383&br=2115&bt=2115&cd=0%7C0%7C0&ch=26&cr=0&cs=0&cv=1&dr=0&ds=3&er=&ft=0J.120FFckag3&l=021626935373918fdbddc0100fff0030a0e39e300000005753bd4&lr=all&mime_type=video_mp4&net=0&pl=0&qs=0&rc=ajhnPDduODdqNTMzNGkzM0ApZjk4aTlnNDs3N2dkZmg0O2dea25sbTNgaW1gLS1kLTBzc14zYjBfYjQwYi4uMzM1NF86Yw%3D%3D&vl=&vr=";
       // String url = "http://v26-web.douyinvod.com/77cf626fab334223a98ba3664a4efbb4/60f91f56/video/tos/cn/tos-cn-ve-15/59fb64c120b8473d8897b51ec428929c/?a=6383&br=1656&bt=1656&cd=0%7C0%7C0&ch=10010&cr=0&cs=0&cv=1&dr=0&ds=3&er=&ft=R7GsdRWWFBNFqd.43agDyhGNVgQ&l=202107221432190102121521664517A64E&lr=all&mime_type=video_mp4&net=0&pl=0&qs=0&rc=ajpxcTZqdG5pNDMzNGkzM0ApOGc2ZjQzZmQ7N2ZnZzlnZGczbmU2bWhqb2dgLS1kLTBzc15fLmEvXjAvNjVjYl5jNDE6Yw%3D%3D&vl=&vr=";
        String url = "http://v26-web.douyinvod.com/4f5f993dcffe7efadf52deaf28ba7dd4/60f91fac/video/tos/cn/tos-cn-ve-15/f753a33d0a034de0aa58c9d5a7268fc9/?a=6383&br=1129&bt=1129&cd=0%7C0%7C0&ch=10010&cr=0&cs=0&cv=1&dr=0&ds=3&er=&ft=R7GsdRWWFBNFqd.43agDyhGNVgQ&l=20210722143500010204048230021767AB&lr=all&mime_type=video_mp4&net=0&pl=0&qs=0&rc=amloeGU6Zmk3NjMzNGkzM0ApODNnNDMzaGU1N2Q1PDk8Z2dyL19xcjQwaGRgLS1kLS9zczVhMi40Ll9jYzQuNV4vLWI6Yw%3D%3D&vl=&vr=";
        downVideo(url,"test2");
    }*/

    /**
     * 方法描述: 下载无水印视频方法
     *
     * @param httpUrl
     * @param title
     * @author tarzan
     * @date 2020年08月04日 10:34:09
     */
    public static void downVideo(String httpUrl, String title) {
        String fileAddress = videoSavePath + title + ".mp4";
        int byteRead;
        try {
            URL url = new URL(httpUrl);
            //获取链接
            URLConnection conn = url.openConnection();
            //输入流
            InputStream inStream = conn.getInputStream();
            //封装一个保存文件的路径对象
            File fileSavePath = new File(fileAddress);
            //注:如果保存文件夹不存在,那么则创建该文件夹
            File fileParent = fileSavePath.getParentFile();
            if (!fileParent.exists()) {
                fileParent.mkdirs();
            }
            //写入文件
            FileOutputStream fs = new FileOutputStream(fileSavePath);
            byte[] buffer = new byte[1024];
            while ((byteRead = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteRead);
            }
            inStream.close();
            fs.close();
            System.out.println("\n-----视频保存路径-----\n" + fileSavePath.getAbsolutePath());
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 方法描述: 获取分享视频id
     *
     * @param url
     * @Return {@link String}
     * @author tarzan
     * @date 2020年08月03日 17:36:12
     */
    public static String getItemId(String url) {
        int start = url.indexOf("/video/") + 7;
        int end = url.lastIndexOf("/");
        return url.substring(start, end);
    }

    /**
     * 方法描述: 过滤分享链接的中文汉字
     *
     * @param url
     * @Return {@link String}
     * @author tarzan
     * @date 2020年08月03日 17:36:33
     */
    public static String filterUrl(String url) {
        int start = url.indexOf("http");
        int end = url.lastIndexOf("/");
        return url.substring(start, end);
    }
}

