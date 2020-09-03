package com.ostrovsky.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>Title: BaseEntity</p>
 * <p>Description: 基础实体类，提供公共字段</p>
 *
 * @author ostrovsky
 * @version 1.0.0
 * @since 2020/9/2 14:09
 */
@Getter
@Setter
@ToString
public class BaseEntity {

    /** 主键ID */
    @TableId
    private Long id;

    /** 是否已经删除 */
    @TableLogic
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    private Boolean deleted;

    /** 创建人 */
    @TableField(fill = FieldFill.INSERT)
    private String creator;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /** 修改人 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifier;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modifyTime;

}
