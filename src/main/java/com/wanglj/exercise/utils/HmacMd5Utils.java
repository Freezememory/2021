package com.wanglj.exercise.utils;


import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * @ClassName: HmacMD5SignImpl
 * @Description:HmacMD5Sign签名
 * @Author guoh
 * @Date 2018/8/10 17:27
 */
//@Service
@Slf4j
public class HmacMd5Utils {


    /**
     * MAC算法可选以下多种算法
     */
    public static final String KEY_MAC = "HmacMD5";

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("method", "aip.vsens.express.newGetExpress");//方法名
        map.put("timestamp", "20210829175101");//请求时间戳
        map.put("format", "json");//响应格式。默认为json，可选值:json。
        map.put("app_key", "ElZaOZMhvS8sKj4F16OF3LPKjZMr1Hqa");
        map.put("v", "1.0");//接口版本号。
        map.put("sign_method", "hmac");//签名摘要算法。可选值:md5, hmac。
        HmacMd5Utils utils = new HmacMd5Utils();
        System.out.println(utils.getHmacMD5Signature(map, "9CkrZ26SMBRUk5cz7cpTLPYrqtzXxqK29Jkjo7aEELggrtdef3RvX9FBhw3lYoH2"));
    }

    //@Override
    public String getHmacMD5Signature(Map<String, String> requestParam, String key) {
        String data = formPublicParam(requestParam);
        //String data = createLinkStringByGet(requestParam);
        byte[] inputData = data.getBytes();
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), KEY_MAC);
        Mac mac;
        try {
            mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            return byteArrayToHexString(mac.doFinal(inputData));
        } catch (Exception e) {
            log.error("HmacMD5算法加密失败", e);
        }
        return null;
    }

    /*byte数组转换为HexString*/
    private String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    /**
     * map参数排序
     **/
    private String formPublicParam(Map<String, String> requestParam) {
        //所有的参数，这里使用TreeMap， 好处在于天然有序，默认是字母顺序
        Map<String, String> params = new TreeMap<String, String>();
        params.putAll(requestParam);
        //访问的URL
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<String, String> param : params.entrySet()) {
            //buffer.append(param.getKey() + "=" + param.getValue());
            buffer.append(param.getKey() + param.getValue() + "&");
        }
        String str = buffer.toString().substring(0, buffer.toString().lastIndexOf("&"));
        log.info("排序后参数：{}", str);
        return str;
    }


    /**
     * 　　* 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * 　　* @param params 需要排序并参与字符拼接的参数组
     * 　　* @return 拼接后字符串
     * 　　* @throws UnsupportedEncodingException
     */
    public static String createLinkStringByGet(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            //key = URLEncoder.encode(key, "UTF-8");
            // value = URLEncoder.encode(value, "UTF-8");
            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + value;
            } else {
                prestr = prestr + key + value + "&";
            }
        }
        log.info("排序后参数：{}", prestr);
        return prestr;
    }

}


