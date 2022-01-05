package com.wanglj.exercise.Template;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Wanglj
 * @Date: 2022/1/5 21:07
 * @Description :
 */
@Slf4j
public class SubTemplate1 extends MainTemplate {

    @Override
    DataInfo checkInfo(Incident incident) {
        if (incident.getName().equals("wanglj")) {
            log.info("满足条件1：{}", incident.toString());
            DataInfo dataInfo = new DataInfo();
            dataInfo.setName("wanglj");
            dataInfo.setAge(24);
            return dataInfo;
        }
        return null;
    }


    static class SubTemplate4 extends MainTemplate {

        @Override
        DataInfo checkInfo(Incident incident) {
            if (incident.getCountry().equals("China")) {
                log.info("满足条件4：{}", incident.toString());
                DataInfo dataInfo = new DataInfo();
                dataInfo.setName("peter");
                dataInfo.setAge(18);
                return dataInfo;
            }
            return null;
        }
    }


}
