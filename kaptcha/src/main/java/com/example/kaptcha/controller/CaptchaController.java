package com.example.kaptcha.controller;

import com.example.kaptcha.config.KaptchaConfig;
import com.example.kaptcha.config.SessionConstant;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

@Controller
public class CaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ResponseBody
    @RequestMapping(value = "/getCode")
    public void getCaptchaImage(HttpServletRequest request, HttpServletResponse response){

        //将kaptcha生成的验证码 保存到session中， 下次客户端传过来的时候做验证码对比
        String createdCode = this.defaultKaptcha.createText();
        HttpSession session = request.getSession();
        session.setAttribute(SessionConstant.IMAGE_CODE, createdCode);

        //生成的字符串验证码生成图片验证码
        BufferedImage createdImage = this.defaultKaptcha.createImage(createdCode);

        //将生成的图片验证码 转换成ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //写入图片
        try {

            ImageIO.write(createdImage, "jpg", byteArrayOutputStream);
        }catch (IOException e){

            System.out.println("图片写入byteArrayOutputStream失败");
        }

        //从outputstream中获取二进制图片数据
        byte[] createdImageBytes = byteArrayOutputStream.toByteArray();

        //使用response输出二进制数据 设置输出类型为image/jpeg类型
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        try {

            ServletOutputStream servletOutputStream = response.getOutputStream();
            servletOutputStream.write(createdImageBytes);
            servletOutputStream.flush();
            servletOutputStream.close();

        }catch (IOException e){

            System.out.println("servletOutpuStream写二进制失败");
        }
    }


    /**
     * 验证验证码是否一致
     */
    @ResponseBody
    @GetMapping(value = "/validateCode")
    public String validateCode(HttpServletRequest request, String code){

        String rightCode = (String) request.getSession().getAttribute(SessionConstant.IMAGE_CODE);

        System.out.println("rightCode = " + rightCode + " code = " + code);

        if (Objects.deepEquals(code, rightCode)){

            return "验证码正确";
        }else {

            return "验证码错误";
        }
    }
}
