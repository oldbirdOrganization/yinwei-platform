package com.platform.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 定制加配方案
 * @author oldbirdteam
 * @email oldbirdteam@gmail.com
 * @date 2019-10-26 10:26:47
 */
@ApiModel(value = "SetcaseVo" ,description= "定制加配方案返回参数")
public class SetcaseVo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "编号")
	private Integer id;
	/**
	 * 加配方案主题
	 */
	@ApiModelProperty(value = "加配方案主题")
	private String title;
	/**
	 * 方案详情内容
	 */
	@ApiModelProperty(value = "方案详情内容")
	private String content;
	/**
	 * 方案缩略图片
	 */
	@ApiModelProperty(value = "方案缩略图片")
	private String casePicUrl;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
	private String createBy;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
	/**
	 * 更新人
	 */
	@ApiModelProperty(value = "更新人")
	private String updateBy;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCasePicUrl() {
		return casePicUrl;
	}

	public void setCasePicUrl(String casePicUrl) {
		this.casePicUrl = casePicUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
}
