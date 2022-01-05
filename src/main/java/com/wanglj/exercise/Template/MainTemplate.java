package com.wanglj.exercise.Template;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Wanglj
 * @Date: 2022/1/5 21:00
 * @Description :
 */
@Slf4j
public abstract class MainTemplate {


    public void mainProcess(Incident incident) {
        DataInfo dataInfo = checkInfo(incident);
        if (dataInfo != null) {
            saveInfo(dataInfo);
            sendMessage(dataInfo);
        }
    }

    abstract DataInfo checkInfo(Incident incident);


    private void saveInfo(DataInfo dataInfo) {
        log.info("存储信息：{}", dataInfo.toString());

    }

    private void sendMessage(DataInfo dataInfo) {
        log.info("发送信息：{}", dataInfo.toString());

    }
}
