package top.yuanyii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 李林林
 * @desc
 * @since 2019/12/10 20:12
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class TalkApplication {
    public static void main(String[] args) {
        SpringApplication.run(TalkApplication.class, args);
    }
}
