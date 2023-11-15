package com.kit.iotdb.entity;

import lombok.Builder;
import lombok.Data;

/**
 * 添加说明
 *
 * @author kit
 * @version 1.0
 * @date 2023/11/7 17:02
 */
@Data
@Builder
public class WeatherEntity {
    /**
     * 固定对应Time字段
     */
    private long timestamp;

    /**
     * 采样时间时间戳
     */
    private long samplingTime;

    /**
     * 采样时间字符
     */
    private String samplingTimeStr;

    /**
     * 城市编码
     */
    private Integer cityKey;

    /**
     * 城市
     */
    private String city;

    /**
     * 温度 ℃
     */
    private float temperature;

    /**
     * 湿度 %
     */
    private float humidity;

    /**
     * pm10
     */
    private float pm10;

    /**
     * pm10
     */
    private float pm25;

    /**
     * 空气质量
     */
    private String quality;

    /**
     * 天气描述
     */
    private String remark;
}
