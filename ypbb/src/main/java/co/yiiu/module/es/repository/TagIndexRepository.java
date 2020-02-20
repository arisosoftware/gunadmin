package co.yiiu.module.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import co.yiiu.module.es.pojo.TagIndex;

/**
 * Created by tomoya at 2018/4/24
 */
public interface TagIndexRepository extends ElasticsearchRepository<TagIndex, Integer> {
}
