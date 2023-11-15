package com.kit.iotdb.service.impl;

import com.kit.iotdb.entity.WeatherEntity;
import com.kit.iotdb.mapper.WeatherMapper;
import com.kit.iotdb.service.WeatherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 添加说明
 *
 * @author kit
 * @version 1.0
 * @date 2023/11/7 16:50
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    @Resource
    WeatherMapper weatherMapper;

    @Override
    public Integer addWeather(WeatherEntity testEntity) {
        return weatherMapper.addWeather(testEntity);
    }

    @Override
    public List<WeatherEntity> pageWeather(Integer page, Integer pageSize) {
        if (page == null || page < 1) {
            page = 1;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = 10;
        }
        int offset = (page - 1) * pageSize;
        return weatherMapper.pageWeather(pageSize, offset);
    }

    @Override
    public Integer deleteWeather(String startTime, String endTime) {
        return weatherMapper.deleteWeather(startTime, endTime);
    }
}
