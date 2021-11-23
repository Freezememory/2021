package com.wanglj.exercise.service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

/**
 * @Author: Wanglj
 * @Date: 2021/11/19 9:47
 * @Description :
 */
public class PhotoMergeService {

    public static void main(String[] args) {
        String[] files = {"C:\\Users\\11525\\Pictures\\壁纸\\微信图片_20200907100824.jpg", "C:\\Users\\11525\\Pictures\\壁纸\\雪景.jpg"};
        String outFilePath = "C:\\Users\\11525\\Pictures\\壁纸\\test5.jpg";
        merge(files, outFilePath);
    }

    /**
     * 图片拼接
     *
     * @param files 要拼接的文件列表
     * @return
     */
    public static void merge(String[] files, String outFilePath) {
        try {
            //  读取第一张图片
            File fileOne = new File(files[0]);
            URL url = new URL("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Ftupian.qqjay.com%2Fu%2F2018%2F0222%2F2_163119_13.jpg&refer=http%3A%2F%2Ftupian.qqjay.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639884147&t=ca77f171d5c15ffaed4fcbfbadf74d19");
            BufferedImage imageFirst = ImageIO.read(url);
            //BufferedImage imageFirst = ImageIO.read(fileOne);
            int width = imageFirst.getWidth();// 图片宽度
            int height = imageFirst.getHeight();// 图片高度
            int[] imageArrayFirst = new int[width * height];// 从图片中读取RGB
            imageArrayFirst = imageFirst.getRGB(0, 0, width, height, imageArrayFirst, 0, width);
            //  对第二张图片做相同的处理
            File fileTwo = new File(files[1]);
            BufferedImage imageSecond = ImageIO.read(fileTwo);
            int width1 = imageSecond.getWidth();
            int height1 = imageSecond.getHeight();
            int[] imageArraySecond = new int[width1 * height1];
            imageArraySecond = imageSecond.getRGB(0, 0, width1, height1, imageArraySecond, 0, width1);
            int ww = Math.max(width, width1);
            // 生成新图片
            BufferedImage imageResult = new BufferedImage(ww, height1 + height, BufferedImage.TYPE_INT_RGB);
            getImage(height, width, ww, imageResult, imageArrayFirst, 0);
            getImage(height1, width1, ww, imageResult, imageArraySecond, height);
            ImageIO.write(imageResult, "jpg", new File(outFilePath));// 写图片
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getImage(int height, int width, int maxWidth, BufferedImage imageResult, int[] imageArraySecond, int oldHeight) {
        int k = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < maxWidth; j++) {
                if (width > j) {
                    imageResult.setRGB(j, i + oldHeight, imageArraySecond[k]);
                    k++;
                } else {
                    imageResult.setRGB(j, i + oldHeight, -328966);
                }
            }
        }
    }
}

