package com.wanglj.exercise.crawler;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Wanglj
 * @date 2021/8/5 19:17
 */
@Component
@Slf4j
public class MusicCloudCrawler {

    public static final String url = "https://music.163.com/#/discover/artist/cat?id=1001&initial={}";

    public static void main(String[] args) throws IOException {

        //Document document = Jsoup.connect(StrUtil.format(url, 65)).get();
        //#m-artist-box > li:nth-child(1) > p > a.nm.nm-icn.f-thide.s-fc0

        Document document = Jsoup.connect(StrUtil.format(url, 65)).ignoreContentType(true)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36").timeout(5000).get();
        System.out.println(document);

    }
}
