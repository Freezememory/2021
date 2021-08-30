package com.wanglj.exercise.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

//import com.ailk.agent.context.client.SyncClientConfig;

/**
 * @Filename DigestUtil.java
 * @Description 签名工具类
 * @Version 1.0
 */
public class DigestUtil {

    private static final Logger logger = LoggerFactory.getLogger(DigestUtil.class.getName());
    /**
     * 签名编码
     */
    public static final String UTF8 = "utf-8";
    /**
     * 签名key
     */
    public static final String SIGN_KEY = "sign";

    /**
     * 渠道id key
     */
    public static final String CHANNEL_ID_KEY = "channelId";

    /**
     * 签名类型key，支持DigestALGEnum
     */
    public static final String SIGN_TYPE_KEY = "signType";

    /**
     * utc时间key
     */
    public static final String TIMESTAMP_KEY = "utc_time_stamp";

    /**
     * 签名算法
     *
     * @Filename DigestUtil.java
     * @Description
     * @Version 1.0
     * @Author bohr.qiu
     * @Email qzhanbo@yiji.com
     * @History <li>Author: bohr.qiu</li> <li>Date: 2013-1-5</li> <li>Version:
     * 1.0</li> <li>Content: create</li>
     */
    public static enum DigestALGEnum {
        SHA256("SHA-256"), MD5("MD5");
        private String name;

        DigestALGEnum(String name) {
            this.name = name;
        }

        public static DigestALGEnum getByName(String name) {
            for (DigestALGEnum _enum : values()) {
                if (_enum.getName().equals(name)) {
                    return _enum;
                }
            }
            return null;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 以Map中key的字符顺序排序后签名，如果secretKey不为空，排在最后面签名。<br/>
     * 比如：Map中值如下：<br/>
     * keyA=valueA<br/>
     * keyB=valueB<br/>
     * keyA1=valueA1<br/>
     * <br/>
     * security_check_code为yjf<br/>
     * <p>
     * 待签名字符串为：<br/>
     * keyA=valueA&keyA1=valueA1&keyB=valueByjf<br/>
     * <b>注意:</b>SIGN_KEY不会被签名
     *
     * @param dataMap
     * @param securityCheckKey 密钥
     * @param de               摘要算法
     * @return
     */
    public static <T> String digest(Map<String, T> dataMap,
                                    String securityCheckKey, DigestALGEnum de, String encoding) {
        if (dataMap == null) {
            throw new IllegalArgumentException("数据不能为空");
        }
        if (securityCheckKey == null) {
            throw new IllegalArgumentException("安全校验码数据不能为空");
        }
        if (de == null) {
            throw new IllegalArgumentException("摘要算法不能为空");
        }
        if (StringUtils.isBlank(encoding)) {
            throw new IllegalArgumentException("字符集不能为空");
        }
        TreeMap<String, T> treeMap = new TreeMap<String, T>(dataMap);
        StringBuilder sb = new StringBuilder(securityCheckKey);
        for (Entry<String, T> entry : treeMap.entrySet()) {
            logger.debug(entry.getKey() + ":" + entry.getValue() + " ; ");
            if (entry.getValue() == null) {
                throw new IllegalArgumentException("待签名值不能为空");
            }
            if (entry.getKey().equals(SIGN_KEY)) {
                continue;
            }
            sb.append(entry.getKey()).append(entry.getValue().toString()).append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(securityCheckKey);
        byte[] toDigest;
        try {
            String str = sb.toString();
            toDigest = str.getBytes(encoding);
            if (logger.isDebugEnabled()) {
                logger.debug("待签名url:" + str);
            }
            MessageDigest md = MessageDigest.getInstance(de.getName());
            md.update(toDigest);
            return new String(Hex.encodeHex(md.digest()));
        } catch (Exception e) {
            throw new RuntimeException("签名失败", e);
        }
    }

    public static <T> String digest(Map<String, T> dataMap, String securityCheckKey) {
        return digest(dataMap, securityCheckKey, DigestALGEnum.MD5, "UTF-8");
    }

    private static String sign(String org) throws Exception {
        String str = org;
        byte[] toDigest = str.getBytes("utf-8");
        if (logger.isDebugEnabled()) {
            logger.debug("待签名url:" + str);
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(toDigest);
        return new String(Hex.encodeHex(md.digest()));
    }

    private void transeURL(String url) {
        //URL url=new URL(url);
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("method", "aip.vsens.notice.orderStatusNotice");//方法名
        map.put("timestamp", "20210830171722");//请求时间戳
        map.put("format", "json");//响应格式。默认为json，可选值:json。
        map.put("app_key", "ElZaOZMhvS8sKj4F16OF3LPKjZMr1Hqa");
        map.put("v", "1.0");//接口版本号。
        map.put("sign_method", "md5");//签名摘要算法。可选值:md5, hmac。
        String digest = digest(map, "9CkrZ26SMBRUk5cz7cpTLPYrqtzXxqK29Jkjo7aEELggrtdef3RvX9FBhw3lYoH2");
        System.out.println(digest);

    }

/*	public static void main(String[] args) throws IOException {
//		String appkey="5LUIUd4UtjdmW34eAkOyiQn4g1o7KRIP";
//		String secret="J34tQzsz1T1Wt2lrr5UgaGFRof6VzI2sXpYltYqHao1UdjSVECv2TIU24arDBGje";
//		String SESSION="pCSKFGRViGF8xd8WIwhNpbtVhCN8ROnybJf9bI9pxTcFHD52zPCyQumgOezJJqFs";
	//	String SESSION="";
		
//		String SESSION="Oy4s0Qu5rl68f3IfrpSI6Q8AHjoLHcuLomv2LMTbfWlwPdLCmUtzX9XLXJ5tBGN6";
//		String SESSION="5fgtJjpbvCXKXKMe8JKDM1h8bAgLtDE605dXTSQtEjPu8O3mSnoPBskhSd4kO18p";
		
		//生产
		String appkey="GbhMAOT0VEn4k85R97TWekRqXdpKRSmS";
		String secret="I1pL334vFyjv379tTJVLIkyGnV7RfOBuJpkeoc4rwO3hERSgfDBtBxXnEerXn9q9";
//		String SESSION="l5hpr9jOk008jhtjQDq4S35EspI8RSC01Nbts3GuVOhuBRsmcrqJGt68b9u9rhNn";
//		String SESSION="e9Bv5MF8BaLqIF5HVusD8gjVk3u22egTpdI2WibGShcpykr24GzlC1pxV8F2cc2E";

		Map<String,String> data=new HashMap<String,String>();
		data.put("method", "aip.vsens.staff.organization.syn");
		//data.put("staffCodeList", "[{\"mobile\":\"18601107890\",\"provinceCode\":\"210000\"}]");
		data.put("staffCodeList", "[{\"mobile\":\"15603462001\",\"provinceCode\":\"210000\"}]");
		//data.put("staffCodeList", "[{\"mobile\":\"18601106868\",\"provinceCode\":\"210000\"},{\"mobile\":\"18601109166\",\"provinceCode\":\"210000\"},{\"mobile\":\"18601109001\",\"provinceCode\":\"210000\"}]");
		*//* data.put("method", "woego.trade.order.query");
		data.put("shop_id", "ZBCU4252");
		data.put("woego_id", "ZBCU4252");
		data.put("order_status", "04");
		data.put("session", SESSION);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONDAY, -1); // 得到当前时间后30分钟的时间
		Date bDate = calendar.getTime();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		data.put("order_begin_time", sf.format(bDate));
		data.put("order_end_time", DateUtil.getDateString(DateUtil.YYYYMMDDHHMMSS));
		data.put("page_no", "1");
		data.put("page_size", "100");
		*//*
     *//*data.put("woego_id", "ZBCU4252");
		data.put("method", "woego.trade.order.querydetail");
		data.put("order_id", "5917102614414679");*//*
		
 		data.put("app_key",appkey);
 		data.put("sign_method", "MD5");
 		data.put("timestamp", String.valueOf(DateUtil.getSysDate()));
 		//data.put("session", SESSION);
 		data.put("sign", DigestUtil.digest(data,secret, DigestALGEnum.MD5,"UTF-8"));
 		System.out.println("请求参数:"+data);
 		Map<String, String> respMap = HttpRequestUtil.getResultData("", "https://hs.zxpt.vsens.com/mall/rest/service", "UTF-8", data);
// 		Map<String, String> respMap = HttpRequestUtil.getResultData("", "http://133.0.191.11:18380/mall/rest/service", "UTF-8", data);
 		
 		System.out.println("返回报文:"+respMap);
		
	}
	public static void main1(String[] args) throws IOException {
		Map<String,String> data=new HashMap<String,String>();
		data.put("client_id", "T0n5VZSkk2m731q4BfVGS24CredTxyWt");
		data.put("client_secret", "GqeKFUmVztfWoGaoW57gc0KZ70HAAptBnJrYrK8WIRnKWDuQy2o4LQ3a1otojir5");
		data.put("grant_type", "refresh_token");
		data.put("code", "VdAg1O77cu7QUDjFqZwNyhQoGROp5zbky8437qAlZQiyD9ZtjmXOwPqxzK0ya2Zi");
		Map<String, String> respMap = HttpRequestUtil.getResultData("", "http://aipuat.woego.cn/route/token", "UTF-8", data);
	}
	public static void main2(String[] arg) throws IOException {
		String appkey="5LUIUd4UtjdmW34eAkOyiQn4g1o7KRIP";
		String secret="J34tQzsz1T1Wt2lrr5UgaGFRof6VzI2sXpYltYqHao1UdjSVECv2TIU24arDBGje";
		String SESSION="pCSKFGRViGF8xd8WIwhNpbtVhCN8ROnybJf9bI9pxTcFHD52zPCyQumgOezJJqFs";
		
		Map<String,String> data=new HashMap<String,String>();
		data.put("method", "com.ai.xiaov.staff.queryMatch");
 		data.put("app_key",appkey);
 		data.put("sign_method", "MD5");
 		data.put("timestamp", DateUtil.getDateString("yyyy-MM-dd hh:mm:ss"));
 		data.put("session", SESSION);
 		data.put("format","JSON");
 		data.put("v","1.0");
		data.put("v_id", "11");
 		
 		*//*data.put("ORDER_ID", "D591407140084363");
 		data.put("PRESS_TYPE", "1");
 		data.put("EXPRESS", "10018");
 		data.put("EXPRESS_NAME", "3127535703");
 		data.put("EXPRESS_NO", "3127535703");
 		data.put("REMARK_INFO", "");
 		data.put("SHIP_TYPE", "0");
 		data.put("SUB_ORDER", "[\"S2014071417144700447905\",\"S2014071417144700447906\",\"S2014071417144700447907\",\"D591407140084363D0001\"]");
 		data.put("IMEI_LIST", "[[\"865085020036187\",\"865085020036948\"],[\"865085025496675\"],[],[]]");*//*
		
		String signStr  = "";
		
		String cnType = "md5";
 		if(cnType.equals("md5")){
 			signStr = DigestUtil.digest(data,secret, DigestALGEnum.MD5,"UTF-8");
 		}else
 		if("hmac".equals(cnType)){
 			signStr = DigestUtil.digest(data,secret, DigestALGEnum.SHA256,"UTF-8");
 		}
 		
 		data.put("SIGN", signStr);
 		System.out.println(signStr);
 		//MockHttpServletRequest res = new MockHttpServletRequest("POST", "/route/rest?notifyTime=2013-09-13 16:59&easylifeOrderNo=20130913000000010935&partnerOrderNo=2013091375192647");
		//res.addParameters(data);
 		//HttpClient client = new HttpClient(SyncClientConfig.SERVER_URL);
 		//org.apache.commons.httpclient.HttpClient s= null;
 		org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient(); 
 		if (data != null) {
			 NameValuePair[] param =new NameValuePair[data.size()];
			 int k =0;
			for (Entry<String, String> entry : data.entrySet()) {
				NameValuePair  nvp = new NameValuePair(entry.getKey(), entry.getValue());
				param[k] = nvp;
				k++ ;
			}
			String url = "http://10.37.242.210:8080/route/rest";
			 PostMethod post = new PostMethod(url);  
			 post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");    
		     post.setRequestBody(param);  
		     post.releaseConnection();  
		     try {
					client.executeMethod(post);
					System.out.println(post.getStatusCode());
					if (post.getStatusCode() == HttpStatus.SC_OK) {
						String response = post.getResponseBodyAsString(); 
						System.out.println(response);
					} 
	        } catch (IOException e) { 
	        	e.printStackTrace();
	        } 
		}
 		
 		
 		
		*//*HttpMethod method = new PostMethod(url);
		client.executeMethod(method);*//*
		//request.getParameterMap();
 		
 		//System.out.println(method.getResponseBodyAsString());
	}*/

}
