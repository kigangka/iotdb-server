-- 创建一个名为 root.ln 的数据库
CREATE DATABASE root.ln;
-------------创建时间序列，-------------
-------------具体iotdb相关涉及的概念参考官网文档，这边可以简单理解为root.ln是数据库，weather为表，下面几个时间序列为表字段---------------
-- 采样时间
create timeseries root.ln.weather.samplingTime INT64 encoding=RLE;
-- 采样时间字符（测试用）
create timeseries root.ln.weather.samplingTimeStr TEXT encoding=PLAIN;
-- 城市编码
create timeseries root.ln.weather.cityKey INT64 encoding=RLE;
-- 城市
create timeseries root.ln.weather.city TEXT encoding=DICTIONARY;
-- 温度
create timeseries root.ln.weather.temperature FLOAT encoding=RLE;
-- 湿度
create timeseries root.ln.weather.humidity FLOAT encoding=RLE;
-- pm10
create timeseries root.ln.weather.pm10 FLOAT encoding=RLE;
-- pm2.5
create timeseries root.ln.weather.pm25 FLOAT encoding=RLE;
-- 空气质量
create timeseries root.ln.weather.quality TEXT encoding=DICTIONARY;
-- 其他备注信息
create timeseries root.ln.weather.remark TEXT encoding=PLAIN;