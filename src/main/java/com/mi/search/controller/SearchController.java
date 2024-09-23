package com.mi.search.controller;

import com.mi.search.common.BaseResponse;
import com.mi.search.common.ResultUtils;
import com.mi.search.manager.SearchFacade;
import com.mi.search.model.dto.search.SearchRequest;
import com.mi.search.model.vo.SearchVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author mi11
 * @version 1.0
 * @project common-search-backend
 * @description
 * @ClassName SearchController
 */
@RestController
@RequestMapping("/search")
public class SearchController {


    @Resource
    private SearchFacade searchFacade;

    @PostMapping("/all")
    public BaseResponse<SearchVO> search(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {

        return ResultUtils.success(searchFacade.search(searchRequest,request));
        }
    }

