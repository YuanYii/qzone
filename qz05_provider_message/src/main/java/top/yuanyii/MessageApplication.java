package top.yuanyii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 张丽
 * 2019/12/11 14:47
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class MessageApplication {
    public static void main(String[] args){
        SpringApplication.run(MessageApplication.class, args);
    }
}
