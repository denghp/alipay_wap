package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088511325561483222";

	// 交易安全检验码，由数字和字母组成的32位字符串
	// 如果签名方式设置为“MD5”时，请设置该参数
    public static String key = "dntpal0opeblb2xeropw2222pfxd7w9nwfv";
    // 商户的私钥
    // 如果签名方式设置为“0001”时，请设置该参数
    public static String private_key = "AAAMIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJOOp0rkXE0vJZttMYRl5JhSfAeK1P8udheByVCp2JgocQWQ00HO9X4OmgJsUuCFdi3nGKOYjCsBvKX6dRpGKNL3kbfJz7A88BHL7JGQHsW7V9ue0WUmPG9uvNZneeLgrTK0NaPsdZe+1NtbvBN/N7A0nL1L9CyOfV2Up0tgH49vAgMBAAECgYEAhVyJFscOJTRXaQJnnqH42TKzpp1zpK75TIWgzaa0e9ERVVZvmSrT9fp8d3Qv83ysMxCK1FErXjNTrS+QZw7CFYsZCSI6jUhhrLZeu5O8VqPcx57f4V4dkF1NaJMgUHrJH3E6f+i8JQWzBowXKDKBOZBeAvv7P+aDk5+WPkC3BwECQQDChkkz0DWnx3CIe+XLfGSxKiHHZOEHp1uuaX8f+SEzZqruPe1iLQV6dd1bz09a5S6vAxpItGpL/oZvl14CB0N1AkEAwjCMV3gDNsVgBL02rvSf94/fM8c+Ahit+2QM3qFhcE+u8AnBupGJRPVp4V6kA4DsfisMbQ8YABg+lQumLiZe0wJAITCkYNDPttbSnpb1OGj7DTdxGAJgWtv1Sqb5Z80sGwcTdx7d1/hyjNh7cmTG121vL7FJ1MnjsR+2dGfRRGtU2QJBALUmNMUj31uoo/nHyMtMyC1YuQ7EPsQV4bcxjg/g29S5gKfLcj1opnT9utfSQY5DJlpMLjtOaUUtWLQROeSzRqECQDx00inOd3c/1ntJL9lBsxRt/rVGOQ2cnxVowPep1KOt6tgw54cJryZOEZ2QtJ+sC6CI6YZcWi3xBY/HDnWwFSA=";
    // 支付宝的公钥
    // 如果签名方式设置为“0001”时，请设置该参数
	public static String ali_public_key = "DDDMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCTjqdK5FxNLyWbbTGEZeSYUnwHitT/LnYXgclQqdiYKHEFkNNBzvV+DpoCbFLghXYt5xijmIwrAbyl+nUaRijS95G3yc+wPPARy+yRkB7Fu1fbntFlJjxvbrzWZ3ni4K0ytDWj7HWXvtTbW7wTfzewNJy9S/Qsjn1dlKdLYB+PbwIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持  utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式，选择项：0001(RSA)、MD5
	public static String sign_type = "0001";
	// 无线的产品中，签名方式为rsa时，sign_type需赋值为0001而不是RSA

}
