package top.yuanyii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author 任可
 * @time 2019/12/9  20:42
 */
@SpringBootApplication
@EnableEurekaClient
public class QzoneProviderUser {

    public static void main(String[] args) {
        SpringApplication.run(QzoneProviderUser.class);
    }
}
