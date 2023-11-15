package com.kit.iotdb.common.task;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.kit.iotdb.common.client.WeatherClient;
import com.kit.iotdb.entity.WeatherEntity;
import com.kit.iotdb.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 添加说明
 *
 * @author kit
 * @version 1.0
 * @date 2023/11/10 10:25
 */
@Component
@Slf4j
public class WeatherTask {

    @Resource
    private WeatherClient weatherClient;

    @Resource
    private WeatherService weatherService;

    /**
     * 每5分钟获取一次天气情况
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void timingDingRobotRem() {
        log.info("定时调用API获取天气情况，开始时间：{}", DateUtil.now());
        Integer cityKey = 101190101;
        JSONObject rstJson = weatherClient.getCityWeatherInfo(cityKey);
        if (rstJson.getIntValue("status") == 200) {
            String time = rstJson.getString("time");
            DateTime dateTime = DateUtil.parse(time, DatePattern.NORM_DATETIME_PATTERN);
            JSONObject weatherData = rstJson.getJSONObject("data");
            String humidity = weatherData.getString("shidu");
            if (StrUtil.contains(humidity, "%")) {
                humidity = StrUtil.removeAll(humidity, "%");
            }
            WeatherEntity weatherEntity = WeatherEntity.builder()
                    .samplingTime(dateTime.getTime())
                    .samplingTimeStr(time)
                    .city("'" + rstJson.getJSONObject("cityInfo").getString("city") + "'")
                    .cityKey(cityKey)
                    .temperature(weatherData.getFloat("wendu"))
                    .humidity(NumberUtil.parseFloat(humidity))
                    .pm10(weatherData.getFloat("pm10"))
                    .pm25(weatherData.getFloat("pm25"))
                    .quality("'" + weatherData.getString("quality") + "'")
                    .remark("'" + weatherData.getString("ganmao") + "'")
                    .build();
            Integer execRst = weatherService.addWeather(weatherEntity);
            log.info("IoTDB数据保存返回结果：{}", execRst);
        } else {
            log.error("定时调用API获取天气情况，返回失败结果：{}", rstJson);
        }
        log.info("定时调用API获取天气情况，结束时间：{}", DateUtil.now());
    }
}
