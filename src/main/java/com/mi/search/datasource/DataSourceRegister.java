package com.mi.search.datasource;

import com.mi.search.model.enums.SearchCategoryEnum;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mi11
 * @version 1.0
 * @project common-search-backend
 * @description
 * @ClassName DataSourceRegister
 */
@Component
public class DataSourceRegister {

    @Resource
    private UserDataSource userDataSource;
    @Resource
    private PostDataSource postDataSource;

    @Resource
    private PictureDataSource pictureDataSource;

    private  Map<String,DataSource<T>> dataSourceMap;

    @PostConstruct
    private void doInit(){
        dataSourceMap = new HashMap(){{
            put(SearchCategoryEnum.USER.getValue(),userDataSource);
            put(SearchCategoryEnum.POST.getValue(),postDataSource);
            put(SearchCategoryEnum.PICTURE.getValue(),pictureDataSource);
        }};
    }


    public DataSource getaDataSourceByCategory(String category){
        DataSource dataSource = dataSourceMap.get(category);
        if (dataSource == null){
            return null;
        }
        return dataSource;
    }



}
