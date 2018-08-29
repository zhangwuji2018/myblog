package com.daibing.myblog.dao;

import com.daibing.myblog.pojo.BizType;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 文章类型接口
 * @author: daibing
 * @create: 2018-07-26 15:18
 **/
@Component
public interface TypeDao {

    /**
     * @param id
     * @return 根据id得到type
     */
    BizType getTypeById(@Param("id") int id);

    /**
     * 查询所有的文章分类
     * @return
     */
    List<BizType> getAllType();
}
