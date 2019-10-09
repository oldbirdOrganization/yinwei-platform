package com.platform.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-15 08:03:40
 */
@ApiModel(value = "FeedbackRequest" ,description= "反馈请求参数")
public class FeedbackRequest implements Serializable {
    private static final long serialVersionUID = 1L;


    //类型
    @ApiModelProperty(value = "类型 1-商品相关 2-客户服务 3-优惠活动 4-其他", example = "1")
    private Integer feedType;

    //移动电话
    @ApiModelProperty(value = "手机号码")
    private String mobile;


    //详细内容
    @ApiModelProperty(value = "详细内容")
    private String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getFeedType() {
        return feedType;
    }

    public void setFeedType(Integer feedType) {
        this.feedType = feedType;
    }
}


