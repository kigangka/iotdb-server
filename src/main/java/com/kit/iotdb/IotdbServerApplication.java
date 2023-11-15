package com.kit.iotdb;

import com.dtflys.forest.springboot.annotation.ForestScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author kit
 */
@SpringBootApplication
@EnableScheduling
@Configuration
@ForestScan(basePackages = "com.kit.iotdb.common.client")
public class IotdbServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotdbServerApplication.class, args);
    }

}
