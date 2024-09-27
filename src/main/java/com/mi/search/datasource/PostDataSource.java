package com.mi.search.datasource;

import cn.hutool.http.HttpRequest;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.mi.search.mapper.PostFavourMapper;
import com.mi.search.mapper.PostThumbMapper;
import com.mi.search.model.dto.post.PostQueryRequest;
import com.mi.search.model.entity.Post;
import com.mi.search.model.vo.PostVO;
import com.mi.search.service.PostService;
import com.mi.search.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author mi11
 */
@Service
@Slf4j
public class PostDataSource implements DataSource<PostVO> {


    @Resource
    private PostService postService;


    @Override
    public Page<PostVO> doSearch(String searchText, long pageNumber, long pageSize) {
       return null;
    }

    @Override
    public Page<PostVO> doSearch(String searchText, long pageNumber, long pageSize, HttpServletRequest request) {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        postQueryRequest.setCurrent(pageNumber);
        postQueryRequest.setPageSize(pageSize);
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        return  postService.getPostVOPage(postPage,request);
    }
}




