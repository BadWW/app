package com.ww.app.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    /**
     * 加密
     * @param sSrc：要加密的字符串
     * @param sKey：加密密钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String sSrc, String sKey) throws Exception {
        if(sKey == null){
            System.out.println("sKey为null");
            return null;
        }
        if(sKey.length() != 16){
            System.out.println("sKey长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());
        sSrc = byte2hex(encrypted).toLowerCase();
        return sSrc;
    }

    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for(int i=0;i<b.length;i++){
            stmp = java.lang.Integer.toHexString(b[i] & 0XFF);
            if(stmp.length() == 1){
                hs = hs + "0" + stmp;
            }else{
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 解密
     * @param sSrc：需要解密的字串
     * @param sKey：解密密钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String sSrc, String sKey) throws Exception{
        if(sKey == null){
            System.out.println("sKey为null");
            return null;
        }
        if(sKey.length() != 16){
            System.out.println("sKey长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("ASCII");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] encryted = hex2byte(sSrc);
        byte[] original = cipher.doFinal(encryted);
        String originalString = new String(original);
        return originalString;
    }

    private static byte[] hex2byte(String sSrc) {
        if(sSrc == null){
            return null;
        }
        int l = sSrc.length();
        if(l % 2 == 1){
            return null;
        }
        byte[] b = new byte[l/2];
        for(int i=0;i != l/2;i++){
            b[i] = (byte) Integer.parseInt(sSrc.substring(i*2, i*2 + 2), 16);
        }
        return b;
    }

    public static void main(String[] args) throws Exception {
        String sKey = "0000000000000000";
        String sSrc = "Aa123456";
        long start = System.currentTimeMillis();
        sSrc = encrypt(sSrc, sKey);
        long end = System.currentTimeMillis();
        System.out.println("加密耗时：" + (end - start));
        System.out.println("加密后的字串为：" + sSrc);
        long begin = System.currentTimeMillis();
        String originalString = decrypt(sSrc, sKey);
        long finished = System.currentTimeMillis();
        System.out.println("解密耗时：" + (finished - begin));
        System.out.println("解密后字串为：" + originalString);
    }

}
