package com.wanglj.exercise.mapper;

/**
 * @Author: Wanglj
 * @Date: 2021/12/1 15:17
 * @Description :
 */

import com.wanglj.exercise.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface UserMapper {

    User selectUserByName(String name);

}
