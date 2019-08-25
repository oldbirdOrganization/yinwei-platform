package com.platform.entity;

import java.io.Serializable;


/**
 * @author oldbird
 * @email oldbirdteam@163.com
 * @date 2019-08-15 08:03:41
 */
public class TopicVo implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    private Integer id;
    //案例主题
    private String title;
    //案例内容
    private String content;
    //导航图
    private String avatar;
    //案例主图片
    private String item_pic_url;
    //子标题
    private String subtitle;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getItem_pic_url() {
        return item_pic_url;
    }

    public void setItem_pic_url(String item_pic_url) {
        this.item_pic_url = item_pic_url;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
