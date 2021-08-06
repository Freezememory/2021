package com.wanglj.exercise.utils;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author Wanglj
 * @date 2021/7/20 17:48
 */
public class ExcelUtil {

    /**
     * 导出excel
     *
     * @param fileName  导出文件名称
     * @param sheetName sheet名称
     * @param head      字节码
     * @param data      导出数据
     * @throws IOException
     */
    public static void exportExcel(String fileName, String sheetName, Class head, List data) throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        if (StringUtils.isEmpty(fileName)) {
            fileName = "excelName";
        }
        if (StringUtils.isEmpty(sheetName)) {
            sheetName = "sheet1";
        }
        fileName = new String(fileName.getBytes(), "utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
        EasyExcel.write(response.getOutputStream(), head).sheet(sheetName).doWrite(data);
    }
}
