package com.wanhe.plugin.wechatPayJs;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Component;

import com.wanhe.entity.PluginConfig;
import com.wanhe.plugin.PaymentPlugin;
import com.wanhe.util.SettingUtils;

/**
 * 微信JS支付插件.
 * Created by Holen on 2015/10/20 0020.
 */
@Component("wcPayJsPlugin")
public class WcPayJsPlugin extends PaymentPlugin {
    @Override
    public String getName() {
        return "微信支付(JS支付)";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getAuthor() {
        return SettingUtils.get().getSiteName();
    }

    @Override
    public String getSiteUrl() {
        return SettingUtils.get().getSiteUrl();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.get;
    }

    @Override
    public String getRequestCharset() {
        return "UTF-8";
    }

    /**
     *description:获取预支付id
     *@param url
     *@param xmlParam
     *@return
     * @author ex_yangxiaoyi
     * @see
     */
    public static String getPayNo(String url,String xmlParam){
        DefaultHttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);

        HttpPost httpost = new HttpPost(url); // 设置响应头信息
        httpost.addHeader("Connection", "keep-alive");
        httpost.addHeader("Accept", "*/*");
        httpost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpost.addHeader("Host", "api.mch.weixin.qq.com");
        httpost.addHeader("X-Requested-With", "XMLHttpRequest");
        httpost.addHeader("Cache-Control", "max-age=0");
        httpost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");

        String prepay_id = "";
        try {
            httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
            HttpResponse response = httpclient.execute(httpost);
            String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            if(jsonStr.indexOf("FAIL")!=-1){
                return prepay_id;
            }
            Map map = doXMLParse(jsonStr);
            prepay_id  = (String) map.get("prepay_id");
        } catch (Exception e) {
            // TODO Auto-generated catch block  `
            e.printStackTrace();
        }
        return prepay_id;
    }

    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     * @param strxml
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public static Map doXMLParse(String strxml) throws Exception {
        if(null == strxml || "".equals(strxml)) {
            return null;
        }

        Map m = new HashMap();
        InputStream in = new ByteArrayInputStream(strxml.getBytes());
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while(it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if(children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }

            m.put(k, v);
        }

        //关闭流
        in.close();

        return m;
    }

    /**
     * 获取子结点的xml
     * @param children
     * @return String
     */
    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if(!children.isEmpty()) {
            Iterator it = children.iterator();
            while(it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if(!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    @Override
    public Map<String, Object> getParameterMap(String sn, String description, HttpServletRequest request) {
////        Setting setting = SettingUtils.get();
//        PluginConfig pluginConfig = getPluginConfig();
//        MerchantPayment payment = getPayment(sn);
////        String prepay_id = getPayNo();
//
//        // 微信统一下单，获取(微信)订单号
//        String appid = pluginConfig.getAttribute("appId");
//        String mch_id = pluginConfig.getAttribute("partner");
//        String nonce_str = CodeUtil.code(16);
////      String body = payment.getOrder().getName();
//        String body = "A+社区商品";
////        try {
////            body = new String(payment.getOrder().getName().getBytes("utf-8"),"iso8859-1");
////        } catch (Exception e) {}
//
////        String body = "11111111";
//        String attach = payment.getId().toString();
//        String out_trade_no = payment.getSn();
//        String totalFee = payment.getAmount().multiply(new BigDecimal(100)).setScale(0).toString();
//        String spbill_create_ip = request.getLocalAddr();
//        String notify_url = getWxNotifyUrl(payment.getSn(), NotifyMethod.async);
//        String trade_type = "JSAPI";
////        String openid = payment.getMember().getWxOpenid();
////        if (null == openid) {
////        	
////        }
//        SortedMap<String, String> packageParams = new TreeMap<String, String>();
//        packageParams.put("appid", appid);
//        packageParams.put("mch_id", mch_id);
//        packageParams.put("nonce_str", nonce_str);
//        packageParams.put("body", body);
//        packageParams.put("attach", attach);
//        packageParams.put("out_trade_no", out_trade_no);
//
//        // 这里写的金额为1 分到时修改
//        packageParams.put("total_fee", totalFee);
//        packageParams.put("spbill_create_ip", spbill_create_ip);
//        packageParams.put("notify_url", notify_url);
//
//        packageParams.put("trade_type", trade_type);
//        packageParams.put("openid", null);
//
//        String createOrderURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
//        String xml = genXml(packageParams);
//        String prepay_id = getPayNo(createOrderURL, xml);
//
//        // 支付参数组装
        SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
//        parameterMap.put("appId", pluginConfig.getAttribute("appId"));
//        parameterMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");
//        parameterMap.put("nonceStr", CodeUtil.code(16));
//        parameterMap.put("package", "prepay_id=" + prepay_id);
//        parameterMap.put("signType", "MD5");
//        parameterMap.put("paySign", generateSign(parameterMap));

        return parameterMap;
    }

    @Override
    public boolean verifyNotify(String sn, NotifyMethod notifyMethod, HttpServletRequest request) {
//        PluginConfig pluginConfig = getPluginConfig();
//        MerchantPayment payment = getPayment(sn);
//        if (null != payment) {
//            return true;
//        }
        return false;
    }

    @Override
    public String getNotifyMessage(String sn, NotifyMethod notifyMethod, HttpServletRequest request) {
        return null;
    }

    @Override
    public Integer getTimeout() {
        return 21600;
    }

    public String unifiedorder() {
        return "";
    }

    /**
     * 生成签名
     *
     * @param parameterMap
     *            参数
     * @return 签名
     */
    private String generateSign(SortedMap<String, Object> params) {
        PluginConfig pluginConfig = getPluginConfig();
        
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            Object value = params.get(key);
            if (null == value) {
                continue;
            }
            // sb是用来计算签名的
            sb.append(key);
            sb.append('=');
            sb.append(value);
            sb.append('&');
        }
        sb.append("key=");
        sb.append(pluginConfig.getAttribute("key"));


        String sign = DigestUtils.md5Hex(sb.toString()).toUpperCase();
        return sign;
    }

    /**
     * 生成xml格式的请求参数
     *
     * @param params
     *            参数集合
     * @return
     */
    private String genXml(SortedMap<String, String> params) {
        try {
            PluginConfig pluginConfig = getPluginConfig();

            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("<xml>");
            for (String key : params.keySet()) {
                String value = params.get(key);
                if (StringUtils.isEmpty(value)) {
                    continue;
                }
                // sb是用来计算签名的
                sb.append(key);
                sb.append('=');
                sb.append(value);
                sb.append('&');
                // sb2是用来做请求的xml参数
                sb2.append("<" + key + ">");
                sb2.append(value);
                sb2.append("</" + key + ">");
            }
            sb.append("key=");
            sb.append(pluginConfig.getAttribute("key"));
            String packageSign = null;
            // 生成签名
            packageSign = DigestUtils.md5Hex(new String(sb.toString())).toUpperCase();
            sb2.append("<sign>");
            sb2.append(packageSign);
            sb2.append("</sign>");
            sb2.append("</xml>");

        // 这一步最关键 我们把字符转为 字节后,再使用“ISO8859-1”进行编码，得到“ISO8859-1”的字符串
            return new String(sb2.toString());
//            return sb2.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
