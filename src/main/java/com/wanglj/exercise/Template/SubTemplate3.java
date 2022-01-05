package com.wanglj.exercise.Template;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Wanglj
 * @Date: 2022/1/5 21:07
 * @Description :
 */
@Slf4j
public class SubTemplate3 extends MainTemplate {

    @Override
    DataInfo checkInfo(Incident incident) {
        if (incident.getAddress().equals("hunan")) {
            log.info("满足条件3：{}", incident.toString());
            DataInfo dataInfo = new DataInfo();
            dataInfo.setName("chenjh");
            dataInfo.setAge(27);
            return dataInfo;
        }
        return null;
    }


}
