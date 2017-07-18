package com.jack.gumballs.utils.shortmsg.yunpian;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 短信http接口的java代码调用示例
 * 依赖Apache HttpClient 3.1
 *
 * @author songchao
 * @since 2013-12-1
 */
public class YPSendShortSms {
    // 查账户信息的http地址
    private static String URI_GET_USER_INFO = "http://yunpian.com/v1/user/get.json";

    //通用发送接口的http地址
    private static String URI_SEND_SMS = "http://yunpian.com/v1/sms/send.json";

    // 模板发送接口的http地址
    private static String URI_TPL_SEND_SMS = "http://yunpian.com/v1/sms/tpl_send.json";

    //状态报告获取
//    private static String URI_PULL_STATUS= "http://yunpian.com/v1/sms/pull_status.json";
  //发送语音验证码接口的http地址
    private static String URI_SEND_VOICE = "http://yunpian.com/v1/voice/send.json";
    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";
    
    private static String apikey="641b913995197e16b8868ccce545fbed";

//    public static void main(String[] args) throws IOException, URISyntaxException {
//        //修改为您的apikey
//        String apikey = "641b913995197e16b8868ccce545fbed";
//        //修改为您要发送的手机号
//        String mobile = "15010559017";
//
//        /**************** 查账户信息调用示例 *****************/
//        System.out.println(YPSendShortSms.getUserInfo(apikey));
//
//		String validateCode=NumberUtil.getValidateCode6();//获取6位验证码
//		String content="【人人有份】您的传传账号已创建成功，登录账号为手机号码：15010559017，初始密码为：123412 ，请妥善保管，并尽快修改密码。";
//        //发短信调用示例
//        System.out.println(YPSendShortSms.sendSms(content, mobile));
////        System.out.println(YPSendShortSms.sendVoice(mobile, validateCode));
//    }

    /**
     * 取账户信息
     *
     * @return json格式字符串
     * @throws IOException
     */
    public static String getUserInfo(String apikey) throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        return post(URI_GET_USER_INFO, params);
    }

    /**
     * 通用接口发短信(推荐)
     *
     * @param apikey apikey
     * @param text   　短信内容
     * @param mobile 　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */
    public static String sendSms(String text, String mobile){
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post(URI_SEND_SMS, params);
    }

    /**
     * 通过模板号发送短信(推荐)
     *
     * @param apikey    apikey
     * @param tpl_id    　模板id
     * @param tpl_value 　模板变量值
     * @param mobile    　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */
    public static String tplSendSms(long tpl_id, String tpl_value, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("tpl_id", String.valueOf(tpl_id));
        params.put("tpl_value", tpl_value);
        params.put("mobile", mobile);
        return post(URI_TPL_SEND_SMS, params);
    }

    
    /**
    * 通过接口发送语音验证码
    * @param mobile 接收的手机号
    * @param code   验证码
    * @return
    */

    public static String sendVoice(String mobile, String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("mobile", mobile);
        params.put("code", code);
        params.put("display_num", "4008000000");
        return post(URI_SEND_VOICE, params);
    }

    /**
     * 基于HttpClient 3.1的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */
    public static String post(String url, Map<String, String> paramsMap) {
        HttpClient client = new HttpClient();
        try {
            PostMethod method = new PostMethod(url);
            if (paramsMap != null) {
                NameValuePair[] namePairs = new NameValuePair[paramsMap.size()];
                int i = 0;
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new NameValuePair(param.getKey(), param.getValue());
                    namePairs[i++] = pair;
                }
                method.setRequestBody(namePairs);
                HttpMethodParams param = method.getParams();
                param.setContentCharset(ENCODING);
            }
            client.executeMethod(method);
            return method.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}