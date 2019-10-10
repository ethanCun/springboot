package com.czy.demos.rabbitmqdemo;

import com.czy.demos.rabbitmqdemo.config.FanoutExchange.ApiReportSender;
import com.czy.demos.rabbitmqdemo.config.HedaersExchange.ApiCreditSender;
import com.czy.demos.rabbitmqdemo.config.DirectExchange.PayNotifySender;
import com.czy.demos.rabbitmqdemo.config.TopicExchange.ApiCoreSender;
import com.czy.demos.rabbitmqdemo.config.TopicExchange.ApiPaymentSender;
import com.czy.demos.rabbitmqdemo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqDemoApplicationTests {

    //DirectExchange
    @Autowired
    private PayNotifySender sender;

    //TopicExchange
    @Autowired
    private ApiCoreSender apiCoreSender;
    @Autowired
    private ApiPaymentSender apiPaymentSender;

    //HeadersExchange
    @Autowired
    private ApiCreditSender apiCreditSender;

    //FanoutExchange
    @Autowired
    private ApiReportSender apiReportSender;

    @Test
    public void contextLoads() {
    }

    //================= DirectExchange ==============
    @Test
    public void testDirectExchange(){

        sender.sender("订单号:" + System.currentTimeMillis());
    }

    @Test
    public void testDirectExchangeObj(){

        User user = new User();
        user.setUsername("czy");
        user.setPassword("123456");

        //测试RPC 发送并接受
        sender.sendObjAndReceive(user);
    }

    @Test
    public void test_sender_many2one_1() throws Exception {
        for (int i = 0; i < 20; i+=2) {
            sender.sender("支付订单号："+i);
            Thread.sleep(1000);
        }
    }

    @Test
    public void test_sender_many2one_2() throws Exception {
        for (int i = 1; i < 20; i+=2) {
            sender.sender("支付订单号："+i);
            Thread.sleep(1000);
        }
    }

    //================== TopicExchange ===============
    @Test
    public void testTopicExchangeApiCoreUser(){

        apiCoreSender.user("user");
    }

    @Test
    public void testTopicExchangeAPiCoreUserQuery(){

        apiCoreSender.userQuery("userQuery");
    }

    @Test
    public void testTopicPaymentExchangeApiPaymentOrder(){

        apiPaymentSender.order("order");
    }

    @Test
    public void testTopicPaymentExchangeApiPaymentOrderQuery(){

        apiPaymentSender.orderQuery("orderQuery");
    }

    @Test
    public void testTopicPaymentExchangeApiPaymentOrderDetailQuery(){

        apiPaymentSender.orderDetailQuery("orderDetailQuery");
    }


    //=============== HeadersExchange ==============
    @Test
    public void testHeadersExchangeCreditBank1(){

        Map<String, Object> headerValues = new HashMap<>();
        headerValues.put("type", "cash");

        apiCreditSender.creditBank(headerValues, "部分匹配");
    }

    @Test
    public void testHeadersExchangeCreditBank2(){

        Map<String, Object> headerValues = new HashMap<>();
        headerValues.put("type", "cash");
        headerValues.put("aging", "fast");

        apiCreditSender.creditBank(headerValues, "全部匹配");
    }

    @Test
    public void testHeadersExchangeCreditFinance1(){

        Map<String, Object> headerValues = new HashMap<>();
        headerValues.put("type", "cash");

        apiCreditSender.creditFinance(headerValues, "部分匹配");
    }

    @Test
    public void testHeadersExchangeCreditFinance2(){

        Map<String, Object> headerValues = new HashMap<>();
        headerValues.put("type", "cash");
        headerValues.put("aging", "fast");

        apiCreditSender.creditFinance(headerValues, "全部匹配");
    }


    //============ FanoutExchange ============
    @Test
    public void testFanoutExchangeReport(){

        apiReportSender.report("开始生成报表");
    }
}
