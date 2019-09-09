package com.example.demo.service;

import java.util.concurrent.Future;

public interface async {

    Future<String> asyncTask1() throws Exception;

    Future<String> asyncTask2() throws Exception;

    Future<String> asyncTask3() throws Exception;
}
