package com.wanglj.exercise.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DeviceListenerExcel extends AnalysisEventListener<DeviceExcel> {
    // 容器存储
    private List<DeviceExcel> container;

    // 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
    private static final int BATCH_COUNT = 500;

    List<DeviceExcel> list = new ArrayList<DeviceExcel>();

    public DeviceListenerExcel(List<DeviceExcel> container) {
        this.container = container;
    }

    /**
     * 解析到一条数据:{}
     */
    @Override
    public void invoke(DeviceExcel data, AnalysisContext context) {
        String str = JSON.toJSONString(data);
        if (StringUtils.isNotEmpty(str) && !"{}".equals(str)) {
            list.add(data);
        }
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    /**
     * 所有数据解析完成！
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
    }

    /**
     * 判断size是否大于0 (6条数据演变成5,1)
     */
    private void saveData() {
        if (list.size() > 0) container.addAll(list);
    }
}
