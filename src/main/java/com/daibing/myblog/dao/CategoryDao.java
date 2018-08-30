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
public interface CategoryDao {

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

    /**
     * 更新分类
     * @param type
     */
    void update(BizType type);

    /**
     * 新增分类
     * @param type
     */
    void insert(BizType type);

    /**
     * 删除分类
     * @param cid
     */
    void delete(@Param("cid") Integer cid);
}
