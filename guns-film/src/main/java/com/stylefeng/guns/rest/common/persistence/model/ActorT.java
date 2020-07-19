package com.stylefeng.guns.rest.common.persistence.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 演员表
 * </p>
 *
 * @author jiayiy
 * @since 2020-07-19
 */
@TableName("actor_t")
public class ActorT extends Model<ActorT> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键编号
     */
    @TableId(value = "uuid", type = IdType.AUTO)
    private Integer uuid;
    /**
     * 演员名称
     */
    @TableField("actor_img")
    private String actorImg;
    /**
     * 演员名称
     */
    @TableField("actor_name")
    private String actorName;


    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public String getActorImg() {
        return actorImg;
    }

    public void setActorImg(String actorImg) {
        this.actorImg = actorImg;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    @Override
    protected Serializable pkVal() {
        return this.uuid;
    }

    @Override
    public String toString() {
        return "ActorT{" +
        "uuid=" + uuid +
        ", actorImg=" + actorImg +
        ", actorName=" + actorName +
        "}";
    }
}
