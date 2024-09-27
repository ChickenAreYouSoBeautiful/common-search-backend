package com.mi.search.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mi.search.model.dto.user.UserQueryRequest;
import com.mi.search.model.vo.UserVO;
import com.mi.search.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @author mi11
 */
@Service
@Slf4j
public class UserDataSource implements DataSource<UserVO> {

    @Resource
    private UserService userService;

    @Override
    public Page<UserVO> doSearch(String searchText, long pageNumber, long pageSize) {
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        userQueryRequest.setCurrent(pageNumber);
        userQueryRequest.setPageSize(pageSize);
        return   userService.listUserVOByPage(userQueryRequest);
    }

    @Override
    public Page<UserVO> doSearch(String searchText, long pageNumber, long pageSize, HttpServletRequest request) {

        return doSearch(searchText, pageNumber, pageSize);
    }
}
