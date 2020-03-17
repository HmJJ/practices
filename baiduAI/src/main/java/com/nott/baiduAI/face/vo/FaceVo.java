package com.nott.baiduAI.face.vo;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2020/3/17 9:55
 * @Modified By:
 **/
public class FaceVo {

    private String image; // 图片信息
    @JSONField(name = "image_type")
    private String imageType = "BASE64"; // 图片类型 BASE64\URL\FACE_TOKEN
    @JSONField(name = "face_type")
    private String faceType = "LIVE"; // 人脸的类型 LIVE(生活照)、IDCARD(身份证内置芯片照片)、WATERMARK(带水印的证件照)、CERT(证件照)
    @JSONField(name = "quality_control")
    private String qualityControl = "LOW"; // 图片质量控制
    @JSONField(name = "liveness_control")
    private String livenessControl = "NONE"; // 活体检测控制 NONE、LOW、NORMAL、HIGH

    public FaceVo(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageType() {
        return imageType;
    }

    public String getFaceType() {
        return faceType;
    }

    public String getQualityControl() {
        return qualityControl;
    }

    public String getLivenessControl() {
        return livenessControl;
    }
}
