package com.example.demo.service.imp;

import com.example.demo.dao.AppDao;
import com.example.demo.entity.App;
import com.example.demo.service.AppService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("appService")
public class AppServiceImp implements AppService {

    @Resource
    private AppDao appDao;

    @Override
    public PageInfo<App> getAppList(int pageNo, int pageSize) {

        //注意：只有紧跟在 PageHelper.startPage 方法后的第一个 MyBatis 的查询(select)方法会被分页
        //pageHelper
        PageHelper.startPage(pageNo, pageSize);

        //所以这个方法一定要紧跟着startPage 不仍然不会被分页
        List<App> apps = appDao.getAppList();

        //使用pageInfo包装
        PageInfo<App> appPageInfo = new PageInfo<>(apps);

        return appPageInfo;
    }
}
