package com.platform.vo;

import io.swagger.annotations.ApiModelProperty;

public class MasterWorkerVo implements java.io.Serializable{

    /**
     * Auto-generated: 2019-08-31 12:6:10
     *
     */
    @ApiModelProperty(value="工人师傅姓名",name="userName",example="")
    private String userName;
    @ApiModelProperty(value="联系电话",name="mobile",example="")
    private String mobile;
    @ApiModelProperty(value="评价星级",name="evaluateLevel",example="1至5")
    private int evaluateLevel;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(int evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }
}
