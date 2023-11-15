package com.kit.iotdb.service;

import com.kit.iotdb.entity.WeatherEntity;

import java.util.List;

/**
 * 添加说明
 *
 * @author kit
 * @version 1.0
 * @date 2023/11/7 16:50
 */
public interface WeatherService {

    Integer addWeather(WeatherEntity weatherEntity);

    List<WeatherEntity> pageWeather(Integer page, Integer pageSize);

    Integer deleteWeather(String startTime, String endTime);
}
