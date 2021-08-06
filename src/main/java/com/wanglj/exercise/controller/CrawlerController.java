package com.wanglj.exercise.controller;

import com.wanglj.exercise.crawler.KuWoCrawler;
import com.wanglj.exercise.crawler.QQCrawler;
import com.wanglj.exercise.entity.MusicCrawlerExcel;
import com.wanglj.exercise.utils.ExcelUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
            ExcelUtil.exportExcel("酷我音乐榜", "sheet1", MusicCrawlerExcel.class, list);
//            ExcelUtil.exportExcel("酷我音乐榜", "sheet2", MusicCrawlerExcel.class, HotList);
//            ExcelUtil.exportExcel("酷我音乐榜", "sheet3", MusicCrawlerExcel.class, womanSingerInfoList);
//            ExcelUtil.exportExcel("酷我音乐榜", "sheet4", MusicCrawlerExcel.class, manSingerInfoList);
//            ExcelUtil.exportExcel("酷我音乐榜", "sheet5", MusicCrawlerExcel.class, mvInfoList);
        } catch (Exception e) {
            log.error("导出异常：", e);
        }
    }

    @GetMapping("/exportQQSingerInfo")
    public void exportQQSingerInfo(@RequestParam(value = "s") String s) {
        try {
            List<MusicCrawlerExcel> list = crawler.getQqSingerInfo();
            ExcelUtil.exportExcel("qq音乐歌手信息", null, MusicCrawlerExcel.class, list);
        } catch (Exception e) {
            log.error("导出异常：", e);
        }
    }


}
