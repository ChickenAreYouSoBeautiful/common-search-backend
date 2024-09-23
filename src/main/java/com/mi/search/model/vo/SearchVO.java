package com.mi.search.model.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mi.search.model.entity.Picture;
import com.mi.search.model.entity.Post;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author mi11
 * @version 1.0
 * @project common-search-backend
 * @description
 * @ClassName SearchVO
 */
@Data
public class SearchVO implements Serializable {

    private Page<UserVO> userVOList;

    private Page<PostVO> postVOList;

    private Page<Picture> pictureVOList;

    private List<?> dataList;
}
