package com.example.demo.common.Base;

import com.example.demo.common.ExceptionHandler.CustomException;
import com.example.demo.common.filters.MyDateEditor;
import com.example.demo.common.filters.MyDoubleEditor;
import com.example.demo.common.filters.MyIntegerEdtor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class BaseController {

    //绑定前后端类型转换
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder){

        //允许date为空
        binder.registerCustomEditor(Date.class, new MyDateEditor());
        binder.registerCustomEditor(Double.class, new MyDoubleEditor());
        binder.registerCustomEditor(Integer.class, new MyIntegerEdtor());
    }

    @ResponseBody
    @ExceptionHandler(value = CustomException.class)
    public BaseModel handleException(CustomException exception){

        return BaseModel.fail(null, exception.getMessage(), exception.getCode());
    }
}
