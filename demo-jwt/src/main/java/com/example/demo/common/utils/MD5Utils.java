package com.example.demo.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Utils {

    //盐
    private static final String SALT="czy";

    //算法名称
    private static final String ALGORITH_NAME="md5";

    //加盐次数
    private static final int HASH_INTERATIONS = 2;

    //对密码二次加盐 16
    public static String encrypt(String password){

        return new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(SALT), HASH_INTERATIONS).toHex();
    }

    //二次加盐 用户名+盐 区分大小写
    public static String encrypt(String username, String password){

        return new SimpleHash(ALGORITH_NAME, password, ByteSource.Util.bytes(username+SALT), HASH_INTERATIONS).toHex();
    }
}
