package org.zcy.agriculture.operate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * 启动程序
 * 
 * @author numberone
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = {"org.zcy.agriculture.*"})
@MapperScan(basePackages = {"org.zcy.agriculture.admin.mapper","org.zcy.agriculture.mapper"})
@PropertySource(value = { "classpath:config.properties"})
public class NumberoneApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(NumberoneApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  Numberone启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}