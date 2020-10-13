package com.veeker.mybatis.basic;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：qiaoliang
 */
public abstract class BaseEntity<T extends Serializable,E> extends BeanEntity<T> {

    @TableField(fill = FieldFill.INSERT)
    private E createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private E updateBy;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
    private String remark;

    public BaseEntity() {
    }

    public BaseEntity(T id) {
        super(id);
    }

    public BaseEntity(T id, E createBy, Date createTime, E updateBy, Date updateTime, String remark) {
        super(id);
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.remark = remark;
    }

    public E getCreateBy() {
        return createBy;
    }

    public void setCreateBy(E createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public E getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(E updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
