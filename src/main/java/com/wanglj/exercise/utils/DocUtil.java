package com.wanglj.exercise.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: Wanglj
 * @Date: 2022/1/18 10:02
 * @Description :
 */
@Slf4j
public class DocUtil {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\11525\\Desktop\\test.doc";
        try {
            List<String> list = readWord(filePath);
            list.forEach(log::info);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws Exception
     * @Description: POI 读取  word
     * @create: 2019-07-27 9:48
     * @update logs
     */
    public static List<String> readWord(String filePath) throws Exception {

        List<String> linList = new ArrayList<String>();
        String buffer = "";
        try {
            if (filePath.endsWith(".doc")) {
                HWPFDocument doc = new HWPFDocument(new FileInputStream(new File(filePath)));
                String docContent = doc.getDocumentText();
                linList.add(docContent);
            } else if (filePath.endsWith(".docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                buffer = extractor.getText();
                extractor.close();
                if (buffer.length() > 0) {
                    //使用换行符分割字符串
                    String[] arry = buffer.split("\\n");
                    for (String string : arry) {
                        linList.add(string.trim());
                    }
                }
            } else {
                return null;
            }
            return linList;
        } catch (Exception e) {
            System.out.print("error---->" + filePath);
            e.printStackTrace();
            return null;
        }
    }

}
