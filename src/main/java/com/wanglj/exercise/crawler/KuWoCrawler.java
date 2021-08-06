package com.wanglj.exercise.crawler;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wanglj.exercise.entity.MusicCrawlerExcel;
import com.wanglj.exercise.utils.CrawlerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wanglj
 * @date 2021/8/6 9:28
 */
@Component
@Slf4j
public class KuWoCrawler {

    private static final String newSongUrl = "http://www.kuwo.cn/api/www/bang/bang/musicList?bangId=17&pn={}&rn={}&httpsStatus=1";
    private static final String hotSongUrl = "http://www.kuwo.cn/api/www/bang/bang/musicList?bangId=16&pn={}&rn={}&httpsStatus=1";
    private static final String womenSingerUrl = "http://www.kuwo.cn/api/www/artist/artistInfo?category=2&prefix=&pn={}&rn={}&httpsStatus=1&reqId=e7a27ef0-f668-11eb-9143-b9cc9fe59635";
    private static final String manSingerUrl = "http://www.kuwo.cn/api/www/artist/artistInfo?category=1&prefix=&pn={}=&rn={}&httpsStatus=1";
    private static final String mvUrl = "http://www.kuwo.cn/api/www/music/mvList?pid=236682871&pn={}&rn={}&httpsStatus=1";

    //http://www.kuwo.cn/api/www/music/mvList?pid=236682871&pn=1&rn=30&httpsStatus=1&reqId=abd100a0-f680-11eb-9143-b9cc9fe59635

    private static final Integer songSize = 30;
    private static final Integer singerSize = 102;

    public static void main(String[] args) throws IOException {
        //String document = CrawlerUtils.connect(StrUtil.format(womenSingerUrl,8,102), "utf-8");
        //System.out.println(document);
        KuWoCrawler kuWoCrawler = new KuWoCrawler();
        // kuWoCrawler.getNewSongInfo();
        //kuWoCrawler.getHotSongInfo();
        // kuWoCrawler.getWomanSingerInfo();
        //kuWoCrawler.getManSingerInfo();
        // kuWoCrawler.getMvInfo();
    }

    public List<MusicCrawlerExcel> getNewSongInfo() {

        List<MusicCrawlerExcel> list = new ArrayList<MusicCrawlerExcel>();
        try {
            String document = CrawlerUtils.connect(StrUtil.format(newSongUrl, 2, songSize), "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(document);
            Integer total = jsonObject.getJSONObject("data").getInteger("num");
            int page = total / songSize;
            if (total % songSize > 0) {
                page += 1;
            }
            for (int i = 1; i <= page; i++) {
                String url = StrUtil.format(newSongUrl, i, songSize);
                String pageDocument = CrawlerUtils.connect(url, "utf-8");
                log.info("第{}页：新歌榜地址：{}", i, url);
                JSONObject pageJsonObject = JSONObject.parseObject(pageDocument);
                JSONArray array = pageJsonObject.getJSONObject("data").getJSONArray("musicList");
                for (int j = 0; j < array.size(); j++) {
                    MusicCrawlerExcel excel = new MusicCrawlerExcel();
                    JSONObject object = array.getJSONObject(j);
                    String songName = object.getString("name");
                    String singerName = object.getString("artist");
                    log.info("新歌榜歌曲名：{}，歌手名：{}", songName, singerName);
                    excel.setSongName(songName);
                    excel.setSingerName(singerName);
                    list.add(excel);
                }
            }
        } catch (Exception e) {
            log.error("抓取信息异常：", e);
        }
        return list;
    }


    public List<MusicCrawlerExcel> getHotSongInfo() throws IOException {
        List<MusicCrawlerExcel> list = new ArrayList<MusicCrawlerExcel>();
        try {
            String document = CrawlerUtils.connect(StrUtil.format(hotSongUrl, 2, songSize), "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(document);
            Integer total = jsonObject.getJSONObject("data").getInteger("num");
            int page = total / songSize;
            if (total % songSize > 0) {
                page += 1;
            }
            for (int i = 1; i <= page; i++) {
                String pageDocument = CrawlerUtils.connect(StrUtil.format(hotSongUrl, i, songSize), "utf-8");
                JSONObject pageJsonObject = JSONObject.parseObject(pageDocument);
                JSONArray array = pageJsonObject.getJSONObject("data").getJSONArray("musicList");
                for (int j = 0; j < array.size(); j++) {
                    MusicCrawlerExcel excel = new MusicCrawlerExcel();
                    JSONObject object = array.getJSONObject(j);
                    String songName = object.getString("name");
                    String singerName = object.getString("artist");
                    log.info("热歌榜歌曲名：{}，歌手名：{}", songName, singerName);
                    excel.setSongName(songName);
                    excel.setSingerName(singerName);
                    list.add(excel);
                }
            }
        } catch (Exception e) {
            log.error("抓取信息异常：", e);
        }

        return list;
    }

    public List<MusicCrawlerExcel> getWomanSingerInfo() throws IOException {
        List<MusicCrawlerExcel> list = new ArrayList<MusicCrawlerExcel>();
        try {
            String document = CrawlerUtils.connect(StrUtil.format(womenSingerUrl, 2, singerSize), "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(document);
            Integer total = jsonObject.getJSONObject("data").getInteger("total");
            int page = total / singerSize;
            if (total % singerSize > 0) {
                page += 1;
            }
            for (int i = 1; i <= page; i++) {
                String pageDocument = CrawlerUtils.connect(StrUtil.format(womenSingerUrl, i, singerSize), "utf-8");
                JSONObject pageJsonObject = JSONObject.parseObject(pageDocument);
                JSONArray array = pageJsonObject.getJSONObject("data").getJSONArray("artistList");
                for (int j = 0; j < array.size(); j++) {
                    MusicCrawlerExcel excel = new MusicCrawlerExcel();
                    JSONObject object = array.getJSONObject(j);
                    String singerName = object.getString("name");
                    log.info("女：歌手名：{}", singerName);
                    excel.setSingerName(singerName);
                    list.add(excel);
                }
            }
        } catch (Exception e) {
            log.error("抓取信息异常：", e);
        }
        return list;
    }

    public List<MusicCrawlerExcel> getManSingerInfo() throws IOException {
        List<MusicCrawlerExcel> list = new ArrayList<MusicCrawlerExcel>();
        try {
            String document = CrawlerUtils.connect(StrUtil.format(manSingerUrl, 2, singerSize), "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(document);
            Integer total = jsonObject.getJSONObject("data").getInteger("total");
            int page = total / singerSize;
            if (total % singerSize > 0) {
                page += 1;
            }
            for (int i = 1; i <= page; i++) {
                String pageDocument = CrawlerUtils.connect(StrUtil.format(manSingerUrl, i, singerSize), "utf-8");
                JSONObject pageJsonObject = JSONObject.parseObject(pageDocument);
                JSONArray array = pageJsonObject.getJSONObject("data").getJSONArray("artistList");
                for (int j = 0; j < array.size(); j++) {
                    MusicCrawlerExcel excel = new MusicCrawlerExcel();
                    JSONObject object = array.getJSONObject(j);
                    String singerName = object.getString("name");
                    //String singerName = object.getString("artist");
                    log.info("男：歌手名：{}", singerName);
                    // excel.setSongName(songName);
                    excel.setSingerName(singerName);
                    list.add(excel);
                }
            }
        } catch (Exception e) {
            log.error("抓取信息异常：", e);
        }
        return list;
    }

    public List<MusicCrawlerExcel> getMvInfo() throws IOException {
        List<MusicCrawlerExcel> list = new ArrayList<MusicCrawlerExcel>();
        try {
            String document = CrawlerUtils.connect(StrUtil.format(mvUrl, 1, songSize), "utf-8");
            JSONObject jsonObject = JSONObject.parseObject(document);
            Integer total = jsonObject.getJSONObject("data").getInteger("total");
            int page = total / singerSize;
            if (total % singerSize > 0) {
                page += 1;
            }
            for (int i = 1; i <= page; i++) {
                String url = StrUtil.format(mvUrl, i, songSize);
                log.info("mv第{}页 ，地址：{}", i, url);
                String pageDocument = CrawlerUtils.connect(url, "utf-8");
                JSONObject pageJsonObject = JSONObject.parseObject(pageDocument);
                JSONArray array = pageJsonObject.getJSONObject("data").getJSONArray("mvlist");
                for (int j = 0; j < array.size(); j++) {
                    MusicCrawlerExcel excel = new MusicCrawlerExcel();
                    JSONObject object = array.getJSONObject(j);
                    String songName = object.getString("artist");
                    String singerName = object.getString("name");
                    log.info("mv歌曲名：{}，歌手名：{}", songName, singerName);
                    excel.setSongName(songName);
                    excel.setSingerName(singerName);
                    list.add(excel);
                }
            }
        } catch (Exception e) {
            log.error("抓取信息异常：", e);
        }
        return list;
    }
}
