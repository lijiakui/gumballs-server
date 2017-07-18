package com.jack.gumballs.utils.sign;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * <p>
 * RSA公钥/私钥/签名工具包
 * </p>
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 * 
 * @author IceWee
 * @date 2012-4-26
 * @version 1.0
 */
public class RSAUtils {
 
    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";
     
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";
     
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
     
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
     
    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     * 
     * @param data 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * 
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);
        return Base64.encode(signature.sign());
    }
 
    /**
     * <p>
     * 校验数字签名
     * </p>
     * 
     * @param data 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign 数字签名
     * 
     * @return
     * @throws Exception
     * 
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {
        byte[] keyBytes = Base64.decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(data);
        return signature.verify(Base64.decode(sign));
    }
 
    /**
     * <P>
     * 私钥解密
     * </p>
     * 
     * @param encryptedData 已加密数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }
 
    /**
     * <p>
     * 公钥解密
     * </p>
     * 
     * @param encryptedData 已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }
 
    /**
     * <p>
     * 公钥加密
     * </p>
     * 
     * @param data 源数据
     * @param publicKey 公钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey)
            throws Exception {
        byte[] keyBytes = Base64.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }
 
    /**
     * <p>
     * 私钥加密
     * </p>
     * 
     * @param data 源数据
     * @param privateKey 私钥(BASE64编码)
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }
    
    public static String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCla3Q8r4S/xicP8mXYksfJCNaygIIZaaD7uHXV5m6mB/ZSNdrQQ9sv/DCDmHh0wddMVpDy8a5KwmJkgHJANY3JDPB3AsE0AVXLtx90127e+FJA8LssIAkGXPqgc+sYwImN8XnYjq/0SBzOnz1jn7p+WDnX3HDS+BI+88JMI5nf8QIDAQAB";
    
    public static String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKVrdDyvhL/GJw/y"
			+ "ZdiSx8kI1rKAghlpoPu4ddXmbqYH9lI12tBD2y/8MIOYeHTB10xWkPLxrkrCYmSA"
			+ "ckA1jckM8HcCwTQBVcu3H3TXbt74UkDwuywgCQZc+qBz6xjAiY3xediOr/RIHM6f"
			+ "PWOfun5YOdfccNL4Ej7zwkwjmd/xAgMBAAECgYAx173PZ0eFjUxJ1szvQ9Lk9T/T"
			+ "yzroM1apYjxHNkaSyoVi4g1LgZayz7dCf5eT/rBDtTW5ri7JIecJx1YlmL1eof8i"
			+ "TXC2XSPdaoJ3uww7hb9as19TPPqR8uGvk/ufoCANBIITIE/k7HPb1z9ZjmI3/RW+"
			+ "6y7G97m85UmuI1tLUQJBAM54EOjU7noMUW/Qr2VFAQSrQ7dn83T2WdtF8Sgni+tB"
			+ "pJXOXJZaoghaay+G4ovtq+8BbmtZ8xGWpuzMTQiQ1BUCQQDNGmtA5HuXGaIgAJu1"
			+ "SqVCrrJpibAqmiypsQXfsYJjLQ+vdHhFMlDA8sd6Z93+ZkJJTnNcMgGc98FD7FHh"
			+ "VwdtAkEAmXQpdTNVL4baPBXJVqYrtJGp2bW7/7FdZZidPS/vUWHPdrwSmnkwNq8l"
			+ "PQSuejuibxgMPmNzQh5LYHITywsBZQJAUSP9gFujxUA/0ldLQmp4fKvuKzBsgD4k"
			+ "IzHEOB3ajm+6P9hc6EsihWLGpddHjKhZ2vRtUPD7kGS3ka6BKhuq9QJAFFPMN6vr"
			+ "8XU5Pd9gXu7kFhuSGhzcWHAi8uLHCg+QkSyBbCRglNsPbXryaPBQiXh0Cu2/WdDj"
			+ "ql/aDfDmGd91YQ==";
//    public static void main(String[] args) throws Exception {
//    	String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCla3Q8r4S/xicP8mXYksfJCNaygIIZaaD7uHXV5m6mB/ZSNdrQQ9sv/DCDmHh0wddMVpDy8a5KwmJkgHJANY3JDPB3AsE0AVXLtx90127e+FJA8LssIAkGXPqgc+sYwImN8XnYjq/0SBzOnz1jn7p+WDnX3HDS+BI+88JMI5nf8QIDAQAB";
//    	byte[] enstr=RSAUtils.encryptByPublicKey("{\"custName\":\"15010559017\",\"custPassword\":\"lijiakui\"}".getBytes(), publicKey);
//    	System.out.println(Base64.encode(enstr));
//    	String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKVrdDyvhL/GJw/y"
//    			+ "ZdiSx8kI1rKAghlpoPu4ddXmbqYH9lI12tBD2y/8MIOYeHTB10xWkPLxrkrCYmSA"
//    			+ "ckA1jckM8HcCwTQBVcu3H3TXbt74UkDwuywgCQZc+qBz6xjAiY3xediOr/RIHM6f"
//    			+ "PWOfun5YOdfccNL4Ej7zwkwjmd/xAgMBAAECgYAx173PZ0eFjUxJ1szvQ9Lk9T/T"
//    			+ "yzroM1apYjxHNkaSyoVi4g1LgZayz7dCf5eT/rBDtTW5ri7JIecJx1YlmL1eof8i"
//    			+ "TXC2XSPdaoJ3uww7hb9as19TPPqR8uGvk/ufoCANBIITIE/k7HPb1z9ZjmI3/RW+"
//    			+ "6y7G97m85UmuI1tLUQJBAM54EOjU7noMUW/Qr2VFAQSrQ7dn83T2WdtF8Sgni+tB"
//    			+ "pJXOXJZaoghaay+G4ovtq+8BbmtZ8xGWpuzMTQiQ1BUCQQDNGmtA5HuXGaIgAJu1"
//    			+ "SqVCrrJpibAqmiypsQXfsYJjLQ+vdHhFMlDA8sd6Z93+ZkJJTnNcMgGc98FD7FHh"
//    			+ "VwdtAkEAmXQpdTNVL4baPBXJVqYrtJGp2bW7/7FdZZidPS/vUWHPdrwSmnkwNq8l"
//    			+ "PQSuejuibxgMPmNzQh5LYHITywsBZQJAUSP9gFujxUA/0ldLQmp4fKvuKzBsgD4k"
//    			+ "IzHEOB3ajm+6P9hc6EsihWLGpddHjKhZ2vRtUPD7kGS3ka6BKhuq9QJAFFPMN6vr"
//    			+ "8XU5Pd9gXu7kFhuSGhzcWHAi8uLHCg+QkSyBbCRglNsPbXryaPBQiXh0Cu2/WdDj"
//    			+ "ql/aDfDmGd91YQ==";
//
//
//    	byte[] destr=RSAUtils.decryptByPrivateKey(Base64.decode(Base64.encode(enstr)), privateKey);
//    	System.out.println(new String(destr));
//
//    	String pas="VSraIa0JSeDbtJo44 p0QgV230KaZ4Squb8zI 8zvxfi3hTgyy/pmPbgkIVw5OaZ/Ue2ZZFfvsoZQA00rEXsWD5ccb8uZSwDOWNpI8UV5Dw4b2KQ9/5M/tYeKVSQEY 4idbo/QneK9pdGOYV6Fh /g3b8hsbf3ct0Dv6LwxsDjc=";
//    	pas=pas.replace(" ", "+");
//    	String oldPwd=new String(RSAUtils.decryptByPrivateKey(Base64.decode(pas), RSAUtils.privateKey));
//    	System.out.println(oldPwd);
//	}
}