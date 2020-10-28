package gaea.hubeimobile.statics.base;

import java.io.Serializable;

public interface BaseService<T,PK extends Serializable> {
    int deleteByPrimaryKey(PK id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(PK id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
