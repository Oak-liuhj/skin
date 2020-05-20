package com.ps.skin.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5加密工具类
 *
 * @author liuhj
 * @date 2020/05/20 16:39
 */
public class Md5Util {

    /**
     * MD5加密
     *
     * @param data 待加密数据
     * @return byte[] 消息摘要
     */
    public static byte[] encodeMD5(String data) {
        // 执行消息摘要
        return DigestUtils.md5(data);
    }

    /**
     * MD5加密
     *
     * @param data 待加密数据
     * @return 返回string
     */
    public static String encodeMD5Hex(String data) {
        // 执行消息摘要
        return DigestUtils.md5Hex(data);
    }

    public static void main(String[] args) {
        String s = encodeMD5Hex("123456");
        System.out.println(s);
    }

}
