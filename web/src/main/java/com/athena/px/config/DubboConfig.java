package com.athena.px.config;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description:
 * @Author: ShenLong
 * @CreateDate: 2018/5/24 16:26
 */
@Configuration
@EnableDubboConfig
@PropertySource("dubbo-config.properties")
@DubboComponentScan
public class DubboConfig {

}
