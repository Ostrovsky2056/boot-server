package com.ostrovsky.api.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ostrovsky.common.constants.DbConstant;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>Title: MyMetaObjectHandler</p>
 * <p>Description: 属性自动填充处理器</p>
 *
 * @author ostrovsky
 * @version 1.0.0
 * @since 2020/9/3 10:52
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    /**
     * 插入元对象字段填充（用于插入时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();

        /* 设置"创建时间"和"创建人" */
        setFieldValByName(DbConstant.CREATE_TIME, now, metaObject);
        setFieldValByName(DbConstant.CREATOR, 0, metaObject);

        /* 设置"修改时间"和"修改人" */
        setFieldValByName(DbConstant.MODIFY_TIME, now, metaObject);
        setFieldValByName(DbConstant.MODIFIER, 0, metaObject);
    }

    /**
     * 更新元对象字段填充（用于更新时对公共字段的填充）
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();

        /* 设置"修改时间"和"修改人" */
        setFieldValByName(DbConstant.MODIFY_TIME, now, metaObject);
        setFieldValByName(DbConstant.MODIFIER, 0, metaObject);
    }
}
