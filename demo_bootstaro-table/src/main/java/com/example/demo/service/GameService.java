package com.example.demo.service;


import com.example.demo.domain.Game;
import com.example.demo.domain.Page;

import java.util.Map;

public interface GameService {

    Map<String, Object> findAll(Page page, Game game);

}
