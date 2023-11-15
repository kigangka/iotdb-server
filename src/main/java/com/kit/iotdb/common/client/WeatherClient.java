package com.kit.iotdb.common.client;

import com.alibaba.fastjson.JSONObject;
import com.dtflys.forest.annotation.Get;

/**
 * 添加说明
 *
 * @author kit
 * @version 1.0
 * @date 2023/11/10 10:38
 */
public interface WeatherClient {

    /**
     * 根据城市代码获取城市天气情况
     */
    @Get("http://t.weather.itboy.net/api/weather/city/{0}")
    JSONObject getCityWeatherInfo(Integer cityKey);
}
