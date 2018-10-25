package com.ljt.openapi.demo.demos;

import com.ljt.openapi.demo.Client;
import com.ljt.openapi.demo.Request;
import com.ljt.openapi.demo.Response;
import com.ljt.openapi.demo.constant.*;
import com.ljt.openapi.demo.enums.ApiHost;
import com.ljt.openapi.demo.enums.Method;
import com.ljt.openapi.demo.util.AESUtil;
import com.ljt.openapi.demo.util.HttpUtils;
import com.ljt.openapi.demo.util.MessageDigestUtil;
import com.ljt.openapi.demo.util.PropertiesUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @Project : dcms-openapi-demo
 * @Program Name : com.ljt.openapi.demo.demos.CreditAppCpFacConfDemo  企贷.java
 * @Description : 获取企贷产品配置
 * @author : zjx
 * @Creation Date : 2018年10月24日
 */
public class CreditAppCpFacConfDemo {

    /******************* 以下信息请换成您获取到的密钥 **************************/
    /**
     * aes加密密钥
     */
    private String key = PropertiesUtils.getAESKey();

    /**
     * 产品Key
     */
    private String appKey = PropertiesUtils.getAppKey();
    /**
     * 产品密钥
     */

    private String appSecret = PropertiesUtils.getAppSecret();
    /******************* 以上信息请换成您获取到的密钥 *************************/

    // 日期类型转换
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     *
     * @Description :获取企贷产品配置
     * @throws Exception
     * @Creation Date : 2018年10月24日
     * @Author : zjx
     */
    @Test
    public void createAppCpFacConfTest() throws Exception{
        //企贷产品代码
        String mtFacCd="101425";
        String url = "/v2/cif/apps/mt_fac_cd/{"+mtFacCd+"}/fields";
        Request request = new Request();
        request.setMethod(Method.GET);
        /**
         * 测试环境，生产环境需要修改为 HttpSchema.HTTPS OFFICIAL_API_HOST
         */
        request.setHost(HttpSchema.HTTPS + ApiHost.DEV_API_HOST.getHost()+url);
        request.setAppKey(appKey);
        request.setAppSecret(appSecret);
        request.setTimeout(Constants.DEFAULT_TIMEOUT);
        Map<String, String> headers = new HashMap<>();
        headers.put(SystemHeader.X_CA_NONCE, UUID.randomUUID().toString());
        // （必填）根据期望的Response内容类型设置
        headers.put(HttpHeader.HTTP_HEADER_ACCEPT, "application/json");
        // Body MD5,服务端会校验Body内容是否被篡改,建议Body非Form表单时添加此Header
        //requestBody = AESUtil.encrypt(key, requestBody);
        //headers.put(HttpHeader.HTTP_HEADER_CONTENT_MD5, MessageDigestUtil.base64AndMD5(requestBody));
        // （POST/PUT请求必选）请求Body内容格式
        //headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_JSON);
        request.setHeaders(headers);
        Response response = Client.execute(request);

        if (response.getStatusCode() == 200) {
            System.out.println("decrypt response：" + AESUtil.decrypt(response.getBody(), key));
        }
    }


}
