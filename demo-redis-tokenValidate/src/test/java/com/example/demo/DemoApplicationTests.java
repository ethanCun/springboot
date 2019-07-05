package com.example.demo;

import com.example.demo.common.utils.EncryptHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void  testAes(){

        try {
            String encode =  EncryptHelper.encrypt("czy", "0000000000000000");

            System.out.println("encode = " + encode);

            String decode = EncryptHelper.decrypt(encode, "0000000000000000");

            System.out.println("decode = " + decode);

        }catch (Exception e){

        }
    }

    @Test
    public void  testAes2(){

        try {
            String encode =  EncryptHelper.encrypt("czy");

            System.out.println("encode = " + encode);

            String decode = EncryptHelper.decrypt(encode);

            System.out.println("decode = " + decode);
        }catch (Exception e){

        }
    }
}
