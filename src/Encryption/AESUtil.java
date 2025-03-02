package Encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


/**
 * @Filename: AESUtil.java
 * @Package: Encryption
 * @Version: V1.0.0
 * @Description: 1.
 * @Author: Alan Zhang [initiator@alandevise.com]
 * @Date: 2023年11月02日 23:31
 */

public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 随机生成密钥
     *
     * @return
     */
    public static String getAESRandomKey() {
        SecureRandom random = new SecureRandom();
        long randomKey = random.nextLong();
        return String.valueOf(randomKey);
    }

    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param key     加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) throws Exception {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //通过Base64转码返回
            return byte2Base64(result);
        } catch (Exception ex) {
            System.out.println("加密失败");
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param key
     * @return
     */
    public static String decrypt(String content, String key) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(key));
            //执行操作
            byte[] result = cipher.doFinal(base642Byte(content));
            return new String(result, "utf-8");
        } catch (Exception ex) {
            System.out.println("解密失败");
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String key) {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        try {
            KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            // 此类提供加密的强随机数生成器 (RNG)，该实现在windows上每次生成的key都相同，但是在部分linux或solaris系统上则不同。
            // SecureRandom random = new SecureRandom(key.getBytes());
            // 指定算法名称，不同的系统上生成的key是相同的。
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(key.getBytes());
            //AES 要求密钥长度为 128
            kg.init(256, random);
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("生成加密秘钥异常！");
        }
        return null;
    }

    /**
     * 字节数组转Base64编码
     *
     * @param bytes
     * @return
     */
    public static String byte2Base64(byte[] bytes) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

    /**
     * Base64编码转字节数组
     *
     * @param base64Key
     * @return
     * @throws IOException
     */
    public static byte[] base642Byte(String base64Key) throws IOException {
        // BASE64Decoder decoder = new BASE64Decoder();
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(base64Key);
    }

    public static void main(String[] args) throws Exception {
        String token = "bjhKHAHyiWnhZtypLzDAPw";
        String splitStr = "kehua";
        Long timestamp = System.currentTimeMillis();

        String originalContent = token+splitStr+timestamp;

        // 密钥
        String key = "98496561648451AZAHVGHXQVCXQy";

        // System.out.println("小程序端发来的Token" + );
        String s1 = AESUtil.encrypt(originalContent, key);

        System.out.println("小程序端发来的请求携带类似这样的Token: " + s1);

        System.out.println("该Token是经过AES算法加密，加解密的密钥存放在小程序端和后台服务器（如有需要，密钥也能设置成定时动态变化？）");
        System.out.println("经过后台解密");


        System.out.println("解密结果: "+AESUtil.decrypt(s1, key));

        String decrypt = AESUtil.decrypt(s1, key);
        String[] split = decrypt.split(splitStr);
        System.out.println("以‘kehua’作为分割字符串，前半部份为真实Token，后半部份为该请求的生成时间戳");
        Long l = Long.valueOf(split[1]);
        System.out.println("  ");
        System.out.println("所以该请求生成时的时间戳是: "+l+" 以及真实的Token是: "+split[0]);
        System.out.println("后台进而比较时间戳的“新鲜程度”，可以设定为如果是在20秒内的请求视为合法请求，超时为非法请求");

        System.out.println("[Bug] 缺点是加密的Token在“保鲜期”内是一直有效的");
    }
}