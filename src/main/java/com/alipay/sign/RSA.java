
package com.alipay.sign;

import com.alipay.config.AlipayConfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSA{
	
	public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";
	
	/**
	* RSA签名
	* @param content 待签名数据
	* @param privateKey 商户私钥
	* @param input_charset 编码格式
	* @return 签名值
	*/
	public static String sign(String content, String privateKey, String input_charset)
	{
        try 
        {
        	PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decode(privateKey) ); 
        	KeyFactory keyf 				= KeyFactory.getInstance("RSA");
        	PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update( content.getBytes(input_charset) );

            byte[] signed = signature.sign();
            
            return Base64.encode(signed);
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        
        return null;
    }
	
	/**
	* RSA验签名检查
	* @param content 待签名数据
	* @param sign 签名值
	* @param ali_public_key 支付宝公钥
	* @param input_charset 编码格式
	* @return 布尔值
	*/
	public static boolean verify(String content, String sign, String ali_public_key, String input_charset)
	{
		try 
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        byte[] encodedKey = Base64.decode(ali_public_key);
	        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

		
			java.security.Signature signature = java.security.Signature
			.getInstance(SIGN_ALGORITHMS);
		
			signature.initVerify(pubKey);
			signature.update( content.getBytes(input_charset) );
		
			boolean bverify = signature.verify( Base64.decode(sign) );
			return bverify;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	* 解密
	* @param content 密文
	* @param private_key 商户私钥
	* @param input_charset 编码格式
	* @return 解密后的字符串
	*/
	public static String decrypt(String content, String private_key, String input_charset) throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64.decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), input_charset);
    }

	
	/**
	* 得到私钥
	* @param key 密钥字符串（经过base64编码）
	* @throws Exception
	*/
	public static PrivateKey getPrivateKey(String key) throws Exception {

		byte[] keyBytes;
		
		keyBytes = Base64.decode(key);
		
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		
		return privateKey;
	}

    public static void main(String[] args) throws Exception {

        String notifyData = "JeYSg4T1ubZW3OJ9Maf9mhDLT+6fBLu+iRpzvZhUt20S6VPsQcArqASS+lmgfZFtXIaWfkM0GczJQ+v0QIVtlNYH+LzoTZLhAQ+v7JamUwcDO+lW6Tss+fX+go0zagnl/Q17VrKIhTc5ZSzVwN5wG2Znt8TyEkjHkK88TXjGj7yDpMUF8Ot5Mc35j2xOeOFRNpUVMX8kws0O9REKL4EBo//rjEml7caZPjVrdaFmi+a0+GmN3IsEXHO1ptFBpEU4e93g4Z8gsrEvo7jBEAfGh5w1vKUJ210kynHy+7mT8BArrYI50EJ2yX5E0cygno2OiSne16RiwZmpkFIk94/XsV/PzDzG0TtHIN7eAQJXG4zjAyNDRIGlDyxdXFhe993eQJW739pKcQ1W7WortGJGZ/j0uYGgYxrQV/bEUJQDZH/h26v3gCoVaTeb1Zx1HEyZEJDhz+2n/uMBz4/qPz6SNFRDBzjiZMTYNw7wgMc4h5dV008Wf5rZAeyknLocM+hDOsxGZfcb/gnXNWeYuBmVOyaTs1G8fU3+TccUHudddYe/L78AT5/KYPI55FQBObOU+33N/5aZpbYINFJiv9LqzoegGUiG5M0CKsLX0mpZzO9DHFygOzH/qPMPF0goGybHg5Ep93Dm8c1cPaXqO+NlIzdm78oN/UXBEeGmoIuORdw+zaZEsT/XRHU28Irf2b+5p935tiNWby2J+9nhWXBrSw25GTXAPtpv3wdq18dhWaHVikyMN6YRLlf3/bpS3OpdJepY8Oy2xZQiMBUDe78gakAeI1mL27ksLUrpYGKRLqciP7fjIjlWcIjhfK1MdBHUITsHuLU0yTqSMNlk4eJO+la/zR1Cyj5dpoFpj/9CMVqqZI9LJYnW3NvZfWyVWG1OZN5domeibMDoMd8rhEzhpMHDXLPS3iuFNFIjesVG03seb5lcDnU1LWSiE7NFW+IHXp5RCyKpQiFl1TFDaih7dGnSbpB7iJzvhtUHUEEB+FEb0ZJzkgIrvpwoTvYbET0vMKc9o+b0CoGybGagMPmMc3RTSKRLjVFn14goFGv9RnhxX/GHjHMeyyqZ3U2BdjKUSBVZiOUvagnMlsIY07vpTRiDGh+ieIjU5Z82M7UuzRUD2+Lp5ebz2GTNtxosUD0JGfTw+d7ceuX40ucKiu/f5eCBqXjn5MbmehJUDIUVUA2KVc1WKijL6iknYTMHw+ScIk+MmdWJP4JH3bppmufUR/rSTisRKSz7cL6JppMASOkGyf+q1mazRg+2iNPhXFziHHYDPiw8Oa7fI6ScVSfnAb2VdPNhn9drtR24KrpAfnF07NJJ/srGRiOocRZsY5SgPhTEuGs/eaBReU5erggyhg==";
        String resData = decrypt(notifyData, AlipayConfig.private_key,"UTF-8");
        System.out.println("resData : " + resData);
    }
}
