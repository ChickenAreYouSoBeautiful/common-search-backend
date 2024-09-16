package com.mi.search.model.dto.picture;


import java.io.Serializable;

import com.mi.search.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户查询请求
 *
 * @author mi11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PictureQueryRequest extends PageRequest implements Serializable {
    /**
     * searchText
     */
    private String searchText;


    private static final long serialVersionUID = 1L;
}
