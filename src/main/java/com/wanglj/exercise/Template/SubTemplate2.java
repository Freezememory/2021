package com.wanglj.exercise.Template;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Wanglj
 * @Date: 2022/1/5 21:07
 * @Description :
 */
@Slf4j
public class SubTemplate2 extends MainTemplate {

    @Override
    DataInfo checkInfo(Incident incident) {
        if (incident.getAge() > 25) {
            log.info("满足条件2：{}", incident.toString());
            DataInfo dataInfo = new DataInfo();
            dataInfo.setName("lisq");
            dataInfo.setAge(26);
            return dataInfo;
        }
        return null;
    }


}
