package com.platform.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.platform.utils.JsonDateSerializer;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;


/**
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-15 08:03:40
 */
public class CommentVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //用户评论的类型;0评论的是商品,1评论的是文章
    private Integer type_id;
    //产品Id
    private String value_id;
    //储存为base64编码
    private String content;
    //记录时间
    private Long add_time;
    //状态 是否被管理员批准显示;1是;0未批准显示
    private Integer status;
    //会员Id
    private Long user_id;

    //会员Id
    private UserVo user_info;
    private List<CommentPictureVo> pic_list;

    @ApiModelProperty(value="质量评价星级",name="quality_evaluate_level",example="质量评价星级 1至5")
    private int quality_evaluate_level;
    @ApiModelProperty(value="服务评价星级",name="service_evaluate_level",example="服务评价星级 1至5")
    private int service_evaluate_level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getValue_id() {
        return value_id;
    }

    public void setValue_id(String value_id) {
        this.value_id = value_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public UserVo getUser_info() {
        return user_info;
    }

    public void setUser_info(UserVo user_info) {
        this.user_info = user_info;
    }

    public List<CommentPictureVo> getPic_list() {
        return pic_list;
    }

    public void setPic_list(List<CommentPictureVo> pic_list) {
        this.pic_list = pic_list;
    }

    public int getQuality_evaluate_level() {
        return quality_evaluate_level;
    }

    public void setQuality_evaluate_level(int quality_evaluate_level) {
        this.quality_evaluate_level = quality_evaluate_level;
    }

    public int getService_evaluate_level() {
        return service_evaluate_level;
    }

    public void setService_evaluate_level(int service_evaluate_level) {
        this.service_evaluate_level = service_evaluate_level;
    }
}
