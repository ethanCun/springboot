package com.example.demo.common.utils;

import java.io.*;

public class ByteConvertHelper {

    //将对象转换为byte
    public static byte[] Object2Byte(Object object){

        byte[] bytes = null;

        try {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();

            bytes = byteArrayOutputStream.toByteArray();
            objectOutputStream.close();
            byteArrayOutputStream.close();

        }catch (Exception exp){

            exp.printStackTrace();
        }

        return bytes;
    }

    //将byte[]转换为Object
    public static Object bytes2Object(byte[] bytes){

        Object object = null;

        try {

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();

        }catch (IOException exp){
            exp.printStackTrace();
        }catch (ClassNotFoundException exp){
            exp.printStackTrace();
        }

        return object;
    }
}
