package com.example.demo.common;

import com.example.demo.common.propertyEditor.MyDateEditor;
import com.example.demo.common.propertyEditor.MyDoubleEditor;
import com.example.demo.common.propertyEditor.MyIntegerEdtor;
import com.example.demo.entity.User;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

// 让springboot注册， date验证时的类型转换
// 1: @Configuration @Autowired 扫描重写
// 2: 控制器注册： 公共控制器@InitBinder 见BaseController

//public class CustomDateWebBindingInitializer implements WebBindingInitializer {
//
//    @Override
//    public void initBinder(WebDataBinder binder) {
//
//        //转换日期
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        //yyyy-MM-dd HH:mm:ss 字符串 -> Date第二个参数控制是否允许为空
//        //binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//
//        //1488260000 时间戳字符串 -> Date
//        binder.registerCustomEditor(Date.class, new MyDateEditor());
//    }
//}

//@Configuration
//public class CustomDateEditorConfiguration {

//    @Configuration + @Autowired 让前端到后端的 string -》 date生效
//    @Autowired
//    public void setWebBindingInitializer(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
//
//        requestMappingHandlerAdapter.setWebBindingInitializer(new CustomDateWebBindingInitializer());
//    }
//}

public class BaseController {

    //绑定前后端类型转换
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder){

        //允许date为空
        binder.registerCustomEditor(Date.class, new MyDateEditor());
        binder.registerCustomEditor(Double.class, new MyDoubleEditor());
        binder.registerCustomEditor(Integer.class, new MyIntegerEdtor());
    }
}
