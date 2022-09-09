package com.baoshi.programmer.config.admin;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000121661017";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCfygTDl55m3oXjFkSvL/uHqONozowAhsBRgQ6qfBNjZRgvUUtNsalAbl4MDPBCvrIzLp0Zl+s3RR1YAeecE/fXsmNQa7MRZq99Bm2nS1Z/zjAA8jHI5FAq+S4oLsMLch4DsK0Qzsf7rC6HT5fkOIEm9Nnqy+xjFSgy8w6W2texEmI5A1ExHzFK2TX7sSK1lN+cQp2aw1NJ1KP5EdL+nfSS7Xt2QkVz6nrqvsJJOQlQj/Hvp9FSMTEs581cCTDoILfa45+BVqoTF/4BWwqyXGZ8fZIDDhQllZC0DYua5/xiQRyd9eJ8cOWOIEwCwM9OX5h40rL7UV/2jILYWpLHpXXtAgMBAAECggEAG4d3mKcXkEuIL56XiMMwh6fOPjFPp4uPuLsOZsrnoDk7mHNeEO2p5kLdix/ziNxH4U+Hm3A0AxqyOoZhe99o1M2UFLfpavcRBu5DmA3/whco6BDSCPVk5nJ1xFCCJAeuKh6XQo0NoUuB3KgW2CfwAGEw5iXp/QwZF+YOPmlHvebRH0Y7Oxv3+RZ6CjacfShAl/3jqRtp3rFCpYQb4wxmcs3ifOZrwb04xocuhUi97ASq2/+hdQvEeUQeLpsjqMNXAqYLmgTza4MhrzfA1MXKwxoevpHGrxX/KIgYnB+LOZiROzUfNeNP4g96CgkNN8yg9o/rhZxcWSgehKllRQ43tQKBgQDxpGe/kBQE24nx2ih4g96kGw0vSH63dKJ4Sy9jELKDOP80NeSqe7kb/qZ75jJilCnrxBzHMLYVawUCCidQ7MmYzJEgzIA9hrx4evqUZ0PeM8TrQ678h53tjziqehl6CffXWWFEnbsF3BSVv0V8K3DKLqesFs2bFSPapVF6ivPx3wKBgQCpSI3/vmaLKTepJVnOf7CdfdeYeGzDU54Enm1YWJEOpsMDAsPvoGddtB3IiqEvb7pewsKrKUZ5CHQMq2NDZ6BQjRukujiiF1pK8+wmHCYUIhEOe7Qh9YPF6YNvX4Q5ifehGS0yLGBEGv2VoCqmE9A/g5QdMNWeh2L/BNpMn72JswKBgB1NLVN6WiMYqAqOxLeQbhACQ8AHD26ZkxX5L/7YSH1HDRM8scTGifTOFUwS5uszQj4XotG4o2vHdW0A5DGY9P01WSfcBHTxgKsvZnFWcFFif69znqV6oHjtFBcABVTaHpabfQMACWy1ej2KWxeqgNvTenuQrE/w6U8bGt08OkaLAoGAEPPOHJVRqY/4Hf06kB5jbnIivAcbRThDc8z1wEomRKRYlR2ax411rIGUFfgkCh8TN9gjLIbWotpA3N/w8dgXytlGsmlfsIPMLANfW7Wn7o42zQ+AS4JNru0il22mTSjO3pgp2YNuJIPbbzd57eXADgw8MewpCFYgiM9QmSnKDlcCgYEApkxYEcLfrxetrwVAKDYsWq0m5ggmgmViyrIYkrbrgH4BPDZZmgNhG1X2Z9Oa64+ZEL7qnpjf8A/eFbPmH2ViOv9ncFCw/umMcr/L2DwyNHaYvsLQwKBKxQ8LeZ06WJ6xjv6lITB3s5xY7+IQ40v6SFIFwr5a6SQle6nsbb3FUbM=";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjihtf6Cj4Zruph/xRbPn7DW0fDZiLXGs6ACNFYpzVa6S+T/Tcq7CA0dyyJWzRTxj0yrycG9kvDNB0NSE/heJz0S9/hYxM+If5iyVJoxl8Ihawd2BHNM8HaH7FaBOQSP/Jpxqc4EVjTYUuzfSgd5kxwArHGK2LBjpC9CPHyv8tieCTwOL2R/2T/z8zuTAxvuLRo1IO1pmP5B4BEVPVfXhOxBleH5xHd4elypuatKY357C/CBK1LbZtQFE/7Wzy1qwBqPCEkkU0aaAKjINkZYN75uFPOgrfYodFtYnnt87xqsrLOB+ejP0OUMOg8d3ogOpFZ2KCEAu9STPHSK/avxfvwIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://127.0.0.1:8080/callback/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/BaseProjectSSM/system/index";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志目录
    public static String log_path = "./";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

