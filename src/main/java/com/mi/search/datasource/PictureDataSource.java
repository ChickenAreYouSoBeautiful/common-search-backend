package com.mi.search.datasource;

import cn.hutool.http.HttpRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mi.search.model.entity.Picture;
import com.mi.search.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author mi11
 * @version 1.0
 * @project backend-administration-template
 * @description
 * @ClassName PictureDataSource
 */
@Service
@Slf4j
public class PictureDataSource implements DataSource<Picture> {

    @Resource
    private PictureService pictureService;

    @Override
    public Page<Picture> doSearch(String searchText, long pageNumber, long pageSize) {
        return pictureService.listPictureByPage(searchText,pageNumber,pageSize);
    }

    @Override
    public Page<Picture> doSearch(String searchText, long pageNumber, long pageSize, HttpServletRequest request) {
        return doSearch(searchText,pageNumber,pageSize);
    }
}
