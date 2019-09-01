package com.platform.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class CommentContentVo implements java.io.Serializable{

    /**
     * Auto-generated: 2019-08-31 12:6:10
     *
     */
        @ApiModelProperty(value="类型",name="typeId",example="类型（1-订单评价 2-商品评价）")
        private int typeId;
        @ApiModelProperty(value="对应值",name="valueId",example="对应值订单编号（201900001001）")
        private String valueId;
        @ApiModelProperty(value="质量评价星级",name="qualityEvaluateLevel",example="质量评价星级 1至5")
        private int qualityEvaluateLevel;
        @ApiModelProperty(value="服务评价星级",name="serviceEvaluateLevel",example="服务评价星级 1至5")
        private int serviceEvaluateLevel;
        @ApiModelProperty(value="评价内容",name="content",example="评价内容")
        private String content;
        @ApiModelProperty(value="评价图片",name="imagesList",example="[]")
        private List<String> imagesList;

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }
        public int getTypeId() {
            return typeId;
        }

        public void setValueId(String valueId) {
            this.valueId = valueId;
        }
        public String getValueId() {
            return valueId;
        }

        public void setContent(String content) {
            this.content = content;
        }
        public String getContent() {
            return content;
        }

        public void setImagesList(List<String> imagesList) {
            this.imagesList = imagesList;
        }
        public List<String> getImagesList() {
            return imagesList;
        }

    public int getQualityEvaluateLevel() {
        return qualityEvaluateLevel;
    }

    public void setQualityEvaluateLevel(int qualityEvaluateLevel) {
        this.qualityEvaluateLevel = qualityEvaluateLevel;
    }

    public int getServiceEvaluateLevel() {
        return serviceEvaluateLevel;
    }

    public void setServiceEvaluateLevel(int serviceEvaluateLevel) {
        this.serviceEvaluateLevel = serviceEvaluateLevel;
    }
}
