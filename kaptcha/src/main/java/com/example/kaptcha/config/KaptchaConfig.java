package com.example.kaptcha.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Configuration
public class KaptchaConfig {


    @Value("${captcha.border}")
    private String border;

    @Value("${captcha.border.color}")
    private String borderColor;

    @Value("${captcha.textproducer.font.color}")
    private String fontColor;

    @Value("${captcha.textproducer.font.size}")
    private String fontSize;

    @Value("${captcha.textproducer.font.name}")
    private String fontName;

    @Value("${captcha.textproducer.char.length}")
    private String charLength;

    @Value("${captcha.image.width}")
    private String imageWidth;

    @Value("${captcha.image.height}")
    private String imageHeight;

    @Value("${captcha.session.key}")
    private String sessionKey;

    //自定义配置kaptcha 注入ioc
    @Bean
    public DefaultKaptcha defaultKaptcha(){

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();

        Properties properties = new Properties();

        properties.setProperty("kaptcha.border", this.border);
        properties.setProperty("kaptcha.border.color", this.borderColor);
        properties.setProperty("kaptcha.textproducer.font.color", this.fontColor);
        properties.setProperty("kaptcha.textproducer.font.size", this.fontSize);
        properties.setProperty("kaptcha.textproducer.font.names", this.fontName);
        properties.setProperty("kaptcha.textproducer.char.length", this.charLength);
        properties.setProperty("kaptcha.image.width", this.imageWidth);
        properties.setProperty("kaptcha.image.height", this.imageHeight);
        properties.setProperty("kaptcha.session.key", this.sessionKey);

        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}
