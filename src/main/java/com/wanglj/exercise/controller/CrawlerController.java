package com.wanglj.exercise.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.wanglj.exercise.crawler.KuWoCrawler;
import com.wanglj.exercise.crawler.QQCrawler;
import com.wanglj.exercise.entity.MusicCrawlerExcel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Wanglj
 * @date 2021/8/6 9:45
 */
@RestController
@RequestMapping("/crawler")
@Slf4j
@AllArgsConstructor
public class CrawlerController {

    private final KuWoCrawler kuWoCrawler;

    private final QQCrawler crawler;

    @GetMapping("/exportKuWoMusicCrawlerInfo")
    public void exportKuWoMusicCrawlerInfo(@RequestParam(value = "s") String s) {
        try {
            List<MusicCrawlerExcel> list = kuWoCrawler.getNewSongInfo();
            List<MusicCrawlerExcel> HotList = kuWoCrawler.getHotSongInfo();
            List<MusicCrawlerExcel> womanSingerInfoList = kuWoCrawler.getWomanSingerInfo();
            List<MusicCrawlerExcel> manSingerInfoList = kuWoCrawler.getManSingerInfo();
            List<MusicCrawlerExcel> mvInfoList = kuWoCrawler.getMvInfo();
            // 多sheel导出
            OutputStream outputStream = null;// 输出流
            outputStream = new FileOutputStream(new File("D:/KuWoMusic.xlsx"));
            ExcelWriter excelWriter = EasyExcel.write(outputStream).build();
            WriteSheet test1 = EasyExcel.writerSheet(0, "酷我新歌榜").head(MusicCrawlerExcel.class).build();
            WriteSheet test2 = EasyExcel.writerSheet(1, "酷我热歌榜").head(MusicCrawlerExcel.class).build();
            WriteSheet test3 = EasyExcel.writerSheet(2, "酷我女华语歌手榜").head(MusicCrawlerExcel.class).build();
            WriteSheet test4 = EasyExcel.writerSheet(3, "酷我男华语歌手榜").head(MusicCrawlerExcel.class).build();
            WriteSheet test5 = EasyExcel.writerSheet(4, "酷我mv华语榜").head(MusicCrawlerExcel.class).build();
            excelWriter.write(list, test1).write(HotList, test2).write(womanSingerInfoList, test3).write(manSingerInfoList, test4).write(mvInfoList, test5);
            excelWriter.finish();
        } catch (Exception e) {
            log.error("导出异常：", e);
        }
    }

    @GetMapping("/exportQQSingerInfo")
    public void exportQQSingerInfo(@RequestParam(value = "s") String s) {
        try {
            Map<String, List<MusicCrawlerExcel>> map = crawler.getQqSingerInfo();
            List<MusicCrawlerExcel> manList = map.get("man");
            List<MusicCrawlerExcel> womanList = map.get("woman");
            // 输出流
            OutputStream outputStream = null;
            outputStream = new FileOutputStream(new File("D:/QQSinger.xlsx"));
            ExcelWriter excelWriter = EasyExcel.write(outputStream).build();
            WriteSheet test1 = EasyExcel.writerSheet(0, "qq男华语歌手榜").head(MusicCrawlerExcel.class).build();
            WriteSheet test2 = EasyExcel.writerSheet(1, "qq女华语歌手榜").head(MusicCrawlerExcel.class).build();
            excelWriter.write(manList, test1).write(womanList, test2);
            excelWriter.finish();
        } catch (Exception e) {
            log.error("导出异常：", e);
        }
    }


}
