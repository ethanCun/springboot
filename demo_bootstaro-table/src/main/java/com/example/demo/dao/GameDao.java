package com.example.demo.dao;

import com.example.demo.domain.Game;
import com.example.demo.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GameDao {

    List<Game> findAll(Page page);

    List<Game> findAll();

}
