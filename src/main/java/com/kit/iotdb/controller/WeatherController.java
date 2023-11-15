package com.kit.iotdb.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.kit.iotdb.common.utils.Constant;
import com.kit.iotdb.common.utils.Rst;
import com.kit.iotdb.entity.WeatherEntity;
import com.kit.iotdb.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.RoundingMode;
import java.util.Date;

/**
 * 添加说明
 *
 * @author kit
 * @version 1.0
 * @date 2023/11/7 16:50
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Resource
    WeatherService weatherService;

    /**
     * 新增
     *
     * @return
     */
    @GetMapping("add")
    public Rst add() {
        Date date = new Date();
        // 模拟数据
        // 此处涉及到的字符串的，必须前后加',如下面的city字段，quality字段， remark字段
        WeatherEntity testEntity = WeatherEntity.builder()
                .samplingTime(date.getTime())
                .samplingTimeStr("'" + DateUtil.format(date, "yyyy-MM-dd HH:mm:ss") + "'")
                .cityKey(101190101)
                .city("'南京'")
                .temperature(NumberUtil.parseFloat(StrUtil.toString(RandomUtil.randomDouble(-18.2, 30.5, 1, RoundingMode.HALF_UP))))
                .humidity(NumberUtil.parseFloat(StrUtil.toString(RandomUtil.randomDouble(1, 100, 1, RoundingMode.HALF_UP))))
                .pm10(NumberUtil.parseFloat(StrUtil.toString(RandomUtil.randomDouble(0, 300, 0, RoundingMode.HALF_UP))))
                .pm25(NumberUtil.parseFloat(StrUtil.toString(RandomUtil.randomDouble(0, 300, 1, RoundingMode.HALF_UP))))
                .quality("'" + Constant.QUALITY_OPTIONS[RandomUtil.randomInt(0, 3)] + "'")
                .remark("'模拟插入'").build();
        return Rst.data(weatherService.addWeather(testEntity));
    }

    /**
     * 分页
     *
     * @param page     第几页
     * @param pageSize 每页多少条
     * @return
     */
    @GetMapping("page")
    public Rst page(Integer page, Integer pageSize) {
        return Rst.data(weatherService.pageWeather(page, pageSize));
    }

    /**
     * 删除数据
     * 对于delete语句，其中子句只能包含时间表达式，目前不支持值筛选
     *
     * @param startTime 需要固定格式为yyyy-MM-dd HH:mm:ss
     * @param endTime   需要固定格式为yyyy-MM-dd HH:mm:ss
     * @return
     */
    @GetMapping("delete")
    public Rst delete(String startTime, String endTime) {
        // 官方对于delete语句，其中子句只能包含时间表达式，目前不支持其他值筛选
        return Rst.data(weatherService.deleteWeather(startTime, endTime));
    }
}
