package com.mi.search.model.dto.search;

import com.mi.search.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author mi11
 * @version 1.0
 * @project common-search-backend
 * @description
 * @ClassName SearchRequest
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SearchRequest extends PageRequest {
    private String searchText;

    private String category;
}
