package com.mi.search.manager;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mi.search.common.ErrorCode;
import com.mi.search.datasource.DataSource;
import com.mi.search.datasource.DataSourceRegister;
import com.mi.search.exception.BusinessException;
import com.mi.search.exception.ThrowUtils;
import com.mi.search.model.dto.search.SearchRequest;
import com.mi.search.model.entity.Picture;
import com.mi.search.model.enums.SearchCategoryEnum;
import com.mi.search.model.vo.PostVO;
import com.mi.search.model.vo.SearchVO;
import com.mi.search.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

/**
 * @author mi11
 * @version 1.0
 * @project common-search-backend
 * @description 搜索门面
 * @ClassName SearchFacade
 */
@Slf4j
@Component
public class SearchFacade {

    @Resource
    private DataSourceRegister dataSourceRegister;


    public SearchVO search(SearchRequest searchRequest, HttpServletRequest request) {
        ThrowUtils.throwIf(searchRequest == null, ErrorCode.PARAMS_ERROR);
        String category = searchRequest.getCategory();
        SearchVO searchVO = new SearchVO();
        //查询条件
        String searchText = searchRequest.getSearchText();
        long current = searchRequest.getCurrent();
        long pageSize = searchRequest.getPageSize();
        if (StrUtil.isBlank(category)) {
            //查询用户
            try {
                CompletableFuture<Page<UserVO>> userTask = CompletableFuture.supplyAsync(() -> {
                    DataSource<UserVO> dataSource =  dataSourceRegister.getaDataSourceByCategory(SearchCategoryEnum.USER.getValue());
                    return dataSource.doSearch(searchText, current, pageSize);
                });

                //查询帖子
                CompletableFuture<Page<PostVO>> postTask = CompletableFuture.supplyAsync(() -> {
                    DataSource<PostVO> dataSource = dataSourceRegister.getaDataSourceByCategory(SearchCategoryEnum.POST.getValue());
                    return dataSource.doSearch(searchText, current, pageSize,request);
                });

                //查询图片
                CompletableFuture<Page<Picture>> pictureTask = CompletableFuture.supplyAsync(() -> {
                    DataSource<Picture> dataSource =  dataSourceRegister.getaDataSourceByCategory(SearchCategoryEnum.PICTURE.getValue());
                    return dataSource.doSearch(searchText, current, pageSize);
                });

                Page<UserVO> userVOPage = userTask.join();
                Page<PostVO> postVOPage = postTask.join();
                Page<Picture> picturePage =pictureTask.join();

                searchVO.setUserVOList(userVOPage);
                searchVO.setPostVOList(postVOPage);
                searchVO.setPictureVOList(picturePage);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,e.getMessage());
            }
        } else {
            SearchCategoryEnum searchCategoryEnum = SearchCategoryEnum.getEnumByValue(category);
            if (searchCategoryEnum == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"查寻类型无效");
            }
            DataSource<?> dataSource = dataSourceRegister.getaDataSourceByCategory(category);
            if (dataSource == null){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"未找到"+category+"数据源");
            }
            Page<?> page = dataSource.doSearch(searchText, current, pageSize,request);
            searchVO.setDataList(page.getRecords());
        }
        return searchVO;
    }
}
