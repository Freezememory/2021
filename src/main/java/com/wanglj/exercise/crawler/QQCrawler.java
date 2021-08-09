package com.wanglj.exercise.crawler;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wanglj.exercise.entity.MusicCrawlerExcel;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wanglj
 * @date 2021/8/6 14:52
 */
@Slf4j
@Component
public class QQCrawler {

    private static final String htmlHead = "<html>\n <head></head>\n <body>\n  getUCGI25738961582047115(";

    private static final String htmlEnd = ")\n </body>\n</html>";

    //private static final String womanSingerUrl = "https://u.y.qq.com/cgi-bin/musics.fcg?_=1628235476760&sign=zzbb9903a2eljzkrwpsxjyq1vwgzk194w0f1b830a";
    private static final String womanSingerUrl = "https://u.y.qq.com/cgi-bin/musicu.fcg?callback=getUCGI25738961582047115&g_tk=5381&jsonpCallback=getUCGI25738961582047115&loginUin=0&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0&data={\"comm\":{\"ct\":24,\"cv\":4747474},\"req_1\":{\"module\":\"music.musichallSinger.SingerList\",\"method\":\"GetSingerListIndex\",\"param\":{\"area\":200,\"sex\":{},\"genre\":-100,\"index\":-100,\"sin\":{},\"cur_page\":{}}}}";


    private static final String mvUrl = "https://u.y.qq.com/cgi-bin/musicu.fcg?callback=getUCGI25738961582047115&g_tk=5381&jsonpCallback=getUCGI25738961582047115&loginUin=0&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0&data={\"comm\":{\"cv\":4747474,\"ct\":24,\"format\":\"json\",\"inCharset\":\"utf-8\",\"outCharset\":\"utf-8\",\"notice\":0,\"platform\":\"yqq.json\",\"needNewCode\":1,\"uin\":0,\"g_tk_new_20200303\":5381,\"g_tk\":5381},\"req_1\":{\"module\":\"MvService.MvInfoProServer\",\"method\":\"GetAllocMvInfo\",\"param\":{\"order\":1,\"start\":{},\"size\":{},\"version_id\":8,\"area_id\":16}}}";


    public static void main(String[] args) throws Exception {
       /* Document document = Jsoup.connect(StrUtil.format(womanSingerUrl,0,1)).get();
        String htmlString = document.toString();
        System.out.println(htmlString);*/
        QQCrawler qqCrawler = new QQCrawler();
        qqCrawler.getQQMvInfo();
        //qqCrawler.getQqSingerInfo();
    }

    public Map<String, List<MusicCrawlerExcel>> getQqSingerInfo() throws IOException {
        Map<String, List<MusicCrawlerExcel>> map = new HashMap<String, List<MusicCrawlerExcel>>();
        List<MusicCrawlerExcel> excelList = new ArrayList<MusicCrawlerExcel>();
        List<MusicCrawlerExcel> womenExcelList = new ArrayList<MusicCrawlerExcel>();
        int size = 80;
        for (int a = 0; a < 2; a++) {
            Document document = Jsoup.connect(StrUtil.format(womanSingerUrl, a, 0, 1)).get();
            String htmlString = document.toString();
            String json = htmlString.substring(htmlString.indexOf(htmlHead) + htmlHead.length(), htmlString.indexOf(htmlEnd));
            JSONObject jsonObject = JSON.parseObject(json);
            Integer total = jsonObject.getJSONObject("req_1").getJSONObject("data").getInteger("total");
            int page = total / size;
            if (total % size > 0) {
                page += 1;
            }
            for (int i = 1; i <= page; i++) {
                int pageNum = (i - 1) * size;
                String url = StrUtil.format(womanSingerUrl, a, pageNum, i);
                log.info("第{}页地址：{}", i, url);
                Document pageDocument = Jsoup.connect(url).get();
                String pageHtmlString = pageDocument.toString();
                // log.info("第{}页内容：{}",i ,pageHtmlString);
                String pageJson = pageHtmlString.substring(pageHtmlString.indexOf(htmlHead) + htmlHead.length(), pageHtmlString.indexOf(htmlEnd));
                //log.info("第{}页裁剪后内容：{}",i ,pageJson);
                JSONObject pageJsonObject = JSON.parseObject(pageJson);
                JSONArray jsonArray = pageJsonObject.getJSONObject("req_1").getJSONObject("data").getJSONArray("singerlist");
                for (int j = 0; j < jsonArray.size(); j++) {
                    MusicCrawlerExcel excel = new MusicCrawlerExcel();
                    JSONObject object = jsonArray.getJSONObject(j);
                    String name = object.getString("singer_name");
                    excel.setSongName(name);
                    if (a == 0) {
                        excelList.add(excel);
                    } else {
                        womenExcelList.add(excel);
                    }
                    log.info("页数：{} ,{}歌手(0-男，1-女)， 歌手名称：{}", i, a, name);
                }
            }
        }
        map.put("man", excelList);
        map.put("woman", womenExcelList);
        return map;
    }


    public List<MusicCrawlerExcel> getQQMvInfo() throws IOException {
        List<MusicCrawlerExcel> excelList = new ArrayList<MusicCrawlerExcel>();
        int size = 100;
        Document document = Jsoup.connect(StrUtil.format(mvUrl, 0, 1)).get();
        String htmlString = document.toString();
        String json = htmlString.substring(htmlString.indexOf(htmlHead) + htmlHead.length(), htmlString.indexOf(htmlEnd));
        JSONObject jsonObject = JSON.parseObject(json);
        Integer total = jsonObject.getJSONObject("req_1").getJSONObject("data").getInteger("total");
        int page = total / size;
        if (total % size > 0) {
            page += 1;
        }
        for (int i = 1; i <= page; i++) {
            int pageNum = (i - 1) * size;
            String url = StrUtil.format(mvUrl, pageNum, size);
            log.info("第{}页地址：{}", i, url);
            Document pageDocument = Jsoup.connect(url).get();
            String pageHtmlString = pageDocument.toString();
            // log.info("第{}页内容：{}",i ,pageHtmlString);
            String pageJson = pageHtmlString.substring(pageHtmlString.indexOf(htmlHead) + htmlHead.length(), pageHtmlString.indexOf(htmlEnd));
            //log.info("第{}页裁剪后内容：{}",i ,pageJson);
            JSONObject pageJsonObject = JSON.parseObject(pageJson);
            JSONArray jsonArray = pageJsonObject.getJSONObject("req_1").getJSONObject("data").getJSONArray("list");
            for (int j = 0; j < jsonArray.size(); j++) {
                MusicCrawlerExcel excel = new MusicCrawlerExcel();
                JSONObject object = jsonArray.getJSONObject(j);
                String name = object.getString("title");
                String singerName = object.getJSONArray("singers").getJSONObject(0).getString("name");
                excel.setSongName(name);
                excel.setSingerName(singerName);
                excelList.add(excel);
                log.info("页数：{} ,MV 歌曲名称：{} ，歌手名称：{}", i, name, singerName);
            }
        }
        return excelList;
    }


}
