package top.yuanyii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 任可
 * @time 2019/12/9  20:25
 */

@SpringBootApplication
@EnableEurekaServer
public class QzoneEureka {
    public static void main(String[] args) {
        SpringApplication.run(QzoneEureka.class,args);
    }
}
