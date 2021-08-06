package com.wanglj.exercise.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Wanglj
 * @date 2021/8/6 9:49
 */
@Data
public class MusicCrawlerExcel {

    //@ApiModelProperty(value = "菜名")
    @ExcelProperty("歌曲")
    private String songName;

    //@ApiModelProperty(value = "主料")
    @ExcelProperty("歌手")
    private String singerName;

    //@ApiModelProperty(value = "辅料")
    @ExcelProperty("专辑")
    private String albumName;
}
