package com.wanglj.exercise.repository;

import com.wanglj.exercise.entiy.UserEntiy;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Wanglj
 * @Date: 2022/2/23 10:08
 * @Description :
 */
@Repository
public interface UserRepository extends ElasticsearchRepository<UserEntiy, String> {
}
