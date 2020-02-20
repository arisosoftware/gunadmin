package co.yiiu.module.es.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import co.yiiu.module.es.pojo.TopicIndex;

/**
 * Created by tomoya at 2018/4/24
 */
public interface TopicIndexRepository extends ElasticsearchRepository<TopicIndex, Integer> {
}
