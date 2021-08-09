package com.wanglj.exercise.crawler;

import cn.hutool.core.util.StrUtil;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlInlineFrame;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.wanglj.exercise.entity.MusicCrawlerExcel;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wanglj
 * @date 2021/8/9 15:02
 */
@Component
@Slf4j
public class CloudMusicCrawler {


    //网易云榜单爬虫初始链接
    private static String URL = "https://music.163.com/#/discover/artist/cat?id={}";

    public static void main(String[] args) {
        CloudMusicCrawler cloudMusicCrawler = new CloudMusicCrawler();
        cloudMusicCrawler.searchSong();
    }

    public Map<String, List<MusicCrawlerExcel>> searchSong() {
        try {
            return getSingerInfo(newWebClient());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WebClient newWebClient() {
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);
        // 启动JS
        webClient.getOptions().setJavaScriptEnabled(true);
        //忽略ssl认证
        webClient.getOptions().setUseInsecureSSL(true);
        //禁用Css，可避免自动二次请求CSS进行渲染
        webClient.getOptions().setCssEnabled(false);
        //运行错误时，不抛出异常
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        // 设置Ajax异步
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        /*//代理设置
        webClient.getOptions().getProxyConfig().setProxyHost("125.77.7.131");
        webClient.getOptions().getProxyConfig().setProxyPort(20217);*/
        /*//认证设置
        DefaultCredentialsProvider cred = new DefaultCredentialsProvider();
        cred.addCredentials("AYX4NS7J1T6QGHA5", "GJ3S1KC9FZE8");
        webClient.setCredentialsProvider(cred);*/
        return webClient;
    }

    private static HtmlPage getIframePage(HtmlPage page) {
        DomNode iframe = page.querySelector("iframe");
        if (iframe instanceof HtmlInlineFrame)
            return (HtmlPage) ((HtmlInlineFrame) iframe).getEnclosedPage();
        return page;
    }

    /**
     * 获取网易云榜单信息
     *
     * @param webClient
     * @throws IOException
     */
    public Map<String, List<MusicCrawlerExcel>> getSingerInfo(WebClient webClient) {
        Map<String, List<MusicCrawlerExcel>> map = new HashMap<String, List<MusicCrawlerExcel>>();
        List<MusicCrawlerExcel> manExcelList = new ArrayList<MusicCrawlerExcel>();
        List<MusicCrawlerExcel> womanExcelList = new ArrayList<MusicCrawlerExcel>();
        for (int i = 1001; i < 1003; i++) {
            try {
                final HtmlPage page = webClient.getPage(StrUtil.format(URL, i));
                final HtmlPage iframePage = getIframePage(page);
                Elements top1Elements = Jsoup.parse(iframePage.asXml()).select("#m-artist-box > li");
                for (Element e : top1Elements) {
                    MusicCrawlerExcel excel = new MusicCrawlerExcel();
                    String singerName = e.select("a").text();
                    log.info("【歌手】:{}", singerName);
                    excel.setSingerName(singerName);
                    if (i == 1001) {
                        manExcelList.add(excel);
                    } else {
                        womanExcelList.add(excel);
                    }
                }
            } catch (Exception e1) {
                log.info("【爬取榜单异常】 : ", e1);
                e1.printStackTrace();
            }
        }
        map.put("man", manExcelList);
        map.put("woman", womanExcelList);
        return map;
    }
}
