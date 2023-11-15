package com.kit.iotdb.mapper;

import com.kit.iotdb.entity.WeatherEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 添加说明
 *
 * @author kit
 * @version 1.0
 * @date 2023/11/7 16:52
 */
@Mapper
public interface WeatherMapper {

    Integer addWeather(WeatherEntity weatherEntity);

    List<WeatherEntity> pageWeather(@Param("pageSize") Integer pageSize, @Param("offset") Integer offset);

    Integer deleteWeather(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
