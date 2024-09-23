package com.mi.search.datasource;

import cn.hutool.http.HttpRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mi11
 * @version 1.0
 * @project common-search-backend
 * @description 数据源接口
 * @ClassName DataSource
 */
public interface DataSource <T>{

    /**
     * 抽象搜索方法
     * @param searchText 搜索词
     * @param pageNumber 页数
     * @param pageSize 页大小
     * @return 分页数据
     */
    Page<T> doSearch(String searchText, long pageNumber, long pageSize);

    /**
     * 抽象搜索方法（校验登录）
     * @param searchText 搜索词
     * @param pageNumber 页数
     * @param pageSize 页大小
     * @param request 请求体
     * @return 分页数据
     */
    Page<T> doSearch(String searchText, long pageNumber, long pageSize, HttpServletRequest request);
}
