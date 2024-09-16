package com.mi.search.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.mi.search.common.BaseResponse;
import com.mi.search.common.ErrorCode;
import com.mi.search.common.ResultUtils;
import com.mi.search.exception.BusinessException;
import com.mi.search.exception.ThrowUtils;
import com.mi.search.model.dto.picture.PictureQueryRequest;
import com.mi.search.model.entity.Picture;
import com.mi.search.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户接口
 *

 */
@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Resource
    private PictureService pictureService;

    // region 登录相关





    /**
     * 分页获取图片封装列表
     *
     * @param pictureQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<Picture>> listUserVOByPage(@RequestBody PictureQueryRequest pictureQueryRequest,
                                                        HttpServletRequest request) {
        if (pictureQueryRequest == null || StringUtils.isBlank(pictureQueryRequest.getSearchText())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long current = pictureQueryRequest.getCurrent();
        long size = pictureQueryRequest.getPageSize();
        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);

        return ResultUtils.success(pictureService.listPictureByPage(pictureQueryRequest.getSearchText(),current,size));
    }



}

