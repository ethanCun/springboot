package com.example.demo.service;

import com.example.demo.entity.App;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AppService {

    PageInfo<App> getAppList(int pageNo, int pageSize);

}
