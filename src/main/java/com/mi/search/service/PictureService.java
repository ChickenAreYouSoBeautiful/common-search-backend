package com.mi.search.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mi.search.model.entity.Picture;

/**
 * 用户服务
 *
 * @author mi11

 */
public interface PictureService  {

    /**
     * 分页获取图片列表
     * @param searchText 搜索词
     * @param pageNumber 页数
     * @param pageSize 大小
     * @return
     */
    Page<Picture> listPictureByPage(String searchText, long pageNumber, long pageSize);
}
