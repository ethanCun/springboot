package com.example.demo.service;

import java.util.concurrent.Future;

public interface async {

    Future<String> asyncTask(String content) throws Exception;
}
