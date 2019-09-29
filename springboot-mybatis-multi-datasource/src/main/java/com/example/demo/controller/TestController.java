package com.example.demo.controller;

import com.example.demo.entity.test1.User1;
import com.example.demo.entity.test2.User2;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.test1.test1Service;
import com.example.demo.service.test2.test2Service;

@RestController
public class TestController {

    @Autowired
    private test1Service ts1;
    @Autowired
    private test2Service ts2;

    /**
     * http://localhost:8080/savets1
     * Content-Type:application/json
     * {
     * 	"id":0,
     * 	"name":"1212xx1x"
     * }
     *
     * */
    @RequestMapping(value = "savets1", method = RequestMethod.POST)
    public int test1DataSource(@RequestBody User1 user1){

        System.out.println("user1 = " + user1);

        return this.ts1.save(user1);
    }

    /**
     * http://localhost:8080/savets1
     * Content-Type:application/json
     * {
     * 	"id":0,
     * 	"name":"1212xx1x"
     * }
     * */
    @RequestMapping(value = "savets2", method = RequestMethod.POST)
    public int test2DataSource(@RequestBody User2 user2){

        return this.ts2.save(user2);
    }
}
