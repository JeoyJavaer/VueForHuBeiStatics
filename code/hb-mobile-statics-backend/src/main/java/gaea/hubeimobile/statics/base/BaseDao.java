package gaea.hubeimobile.statics.base;

import java.io.Serializable;

public interface BaseDao<T,PK extends Serializable> {
    int deleteByPrimaryKey(PK id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
