package com.springboot.demo.controller;

import com.springboot.demo.base.controller.BaseController;
import com.springboot.demo.base.utils.StateParameter;
import javafx.geometry.Pos;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/first")
public class FirstController extends BaseController {

    @RequestMapping(value = "/view")
    public String view(HttpServletRequest httpServletRequest){

        return "/demoPage/firstPage";
    }

    @RequestMapping(value = "/back", method = RequestMethod.POST)
    @ResponseBody
    public ModelMap back(){

        return getModelMap(StateParameter.SUCCESS, null, "success");
    }
}
