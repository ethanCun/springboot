package com.example.swaggerui.demo.controller;

import com.example.swaggerui.demo.entity.Person;
import com.example.swaggerui.demo.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/***
 *
 * 文档链接：localhost:8080/swagger-ui.html (项目链接/swagger-ui.html)
 *
 * @Api 描述类/接口的主要用途
 * @ApiOperation 描述方法用途
 * @ApiImplicitParam 描述方法的参数
 * @ApiImplicitParams 描述方法的参数(Multi - Params)
 * @ApiIgnore 忽略某类/方法/参数的文档
 */

@Api(tags = {"hello控制器"})
@RestController
public class HelloController {

    @ApiOperation("测试接口test1")
    /**
     * name:参数名称 value：参数描述 dataType:数据类型 param:参数类型
     *
     * dataType="int" 代表请求参数类型为int类型，当然也可以是Map、User、String等；
     * paramType="body" 代表参数应该放在请求的什么地方:
     *     header-->放在请求头。请求参数的获取：@RequestHeader(代码中接收注解)
     *     query-->用于get请求的参数拼接。请求参数的获取：@RequestParam(代码中接收注解)
     *     path（用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)
     *     body-->放在请求体。请求参数的获取：@RequestBody(代码中接收注解)
     *     form（不常用）
     */
    @ApiImplicitParam(name = "a", value = "输入的字符串", dataType = "String", paramType = "query")
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public String test1(Integer a){

        return a.toString();
    }

    @ApiOperation("test2")
    @ApiImplicitParam(name = "s", value = "输入的字符串", dataType = "String", paramType = "query")
    @GetMapping(value = "/test2")
    public String test2(String s){

        return s;
    }

    @ApiOperation(value = "test3")
    @ApiImplicitParam(name = "s", value = "参数", dataType = "String", paramType = "param")
    @PostMapping(value = "/test3")
    public String test3(String s){

        return s;
    }

    @ApiOperation(value = "test4")
    @ApiImplicitParam(name = "person", value = "用户信息", dataType = "Person", paramType = "body")
    @PostMapping(value = "/test4")
    public String test4(@RequestBody Person person){

        return person.toString();
    }

    @ApiOperation("test5")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "id号码", dataType = "Integer", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "名称", dataType = "String", paramType = "path")
    })
    @GetMapping(value = "/test5/{id}/{name}")
    public String test5(@PathVariable(name = "id") Integer id,
                        @PathVariable(name = "name") String name){

        return "id: " + id.toString() + " name: " + name;
    }

    @ApiOperation(value = "test6")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "name", value = "姓名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "age", value = "年龄", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/test6", method = RequestMethod.GET)
    public String test6(@RequestParam(value = "myname") String name,
                        @RequestParam(value = "myage") String age){

        return "name: " + name + " age: " + age;
    }
}
