package com.mi.search.esdao;

import com.mi.search.model.dto.post.PostEsDTO;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 帖子 ES 操作
 *
 * @author mi11
 */
public interface PostEsDao extends ElasticsearchRepository<PostEsDTO, Long> {

    /**
     * 根据用户名查询
     * @param userId
     * @return
     */
    List<PostEsDTO> findByUserId(Long userId);

    /**
     * 根据id和用户名查询
     * @param id
     * @param userId
     * @return
     */
    List<PostEsDTO> findByIdAndUserId(Long id,Long userId);
}
