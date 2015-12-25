package com.dou361.third_pay;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;

/**
 * ========================================
 * <p/>
 * 版 权：深圳市晶网电子科技有限公司 版权所有 （C） 2015
 * <p/>
 * 作 者：陈冠明
 * <p/>
 * 个人网站：http://www.dou361.com
 * <p/>
 * 版 本：1.0
 * <p/>
 * 创建日期：2015/11/9
 * <p/>
 * 描 述：
 * <p/>
 * <p/>
 * 修订历史：
 * <p/>
 * ========================================
 */
public class AliPayUtils {

    // 商户PID
    //public static final String PARTNER = "2088702697495412";
    public static final String PARTNER = "2088311071300602";
    // 商户收款账号
    //public static final String SELLER = "13923042902";
    public static final String SELLER = "JWdz2014@163.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICWwIBAAKBgQDUReujwZ59IIGULeU825JfQFY/Wp+CS+aqjTM7A1uFu5Ghvn9XCIfW//Q+3evxrV/obrrHcqU36oE7fmLf0gtD6282lbbErzfB65XGC4H6XCF6kAhaG4+Vtg2LYFxgXjvXTlGQuDCH1p4NT/6TyrwA1xvm5t2iOr9PiEGM3Hq7RQIDAQABAoGADXEYCNib5qSGUBHP8FifHjT+wShF7YRM6QPj+Jqv0vobsLhVa/d4Up8Gomx/ImCuMBPC9BpIaBALeu735+BvIhJihNRVSuQZRgCBuIVDVOw6eDSK4yb/s0HBQww4Auf5L9mIxlMqWC2BkSwUWLsxtpOiPrfff41utCJR+XmxhoECQQDp6XijsO2YdADwvlva3xBzsPYxj+EAsbaSdZLCf6xl2ys2n9uw4v0ZRN+mSaL4GtE2yiDQICvGPn/rh1Ra9WnVAkEA6FFbUUJ25SiLM9tsOgWZ3Z7BYGCOs8wXD5M7w4yQcImkpUnV2X1zjf0Zkyi9CMNf59NLUy4DyKDKnTbXL0rTsQJAdGanjyXLhpMrqKHCCg/5GulUCmPj/zlwdj+l8b4hJ2rCfWNdqw+6DaoVdaWtQLPvqsk5PPjVb8jIVYe5ta3spQJAPtfDG/gesV6GSUDRxeA9/vUy2h9Eet56Xnw1bnBXohmZsByI/Iwy9fXBNU2P1Q8EonFFrvqDi4HzTRPIocJcAQJAH7HWMaXDHXFAXLgiHsCMoj6uES8JHlYko1bIBXYRszW0ukOxhrmtclqr0jLo09xA6kNn9OvxoWDF76kiWDb0oQ==";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
    public static final int SDK_PAY_FLAG = 1;

    public static final int SDK_CHECK_FLAG = 2;

    /**
     * get the sdk version. 获取SDK版本号
     *
     */
    public void getSDKVersion(Activity activity) {
        PayTask payTask = new PayTask(activity);
        String version = payTask.getVersion();
    }

    /**
     * create the order info. 创建订单信息
     *
     */
    public static String getOrderInfo(String orderNum,String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + orderNum + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm"
                + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }


    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    public static String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    public static String getSignType() {
        return "sign_type=\"RSA\"";
    }

}
