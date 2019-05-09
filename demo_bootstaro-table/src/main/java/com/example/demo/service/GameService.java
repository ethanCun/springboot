package com.example.demo.service;


import java.util.Map;

public interface GameService {

    Map<String, Object> findAll(int page, int rows);
}
