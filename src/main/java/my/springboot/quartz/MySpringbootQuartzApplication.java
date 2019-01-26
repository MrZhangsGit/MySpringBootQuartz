package my.springboot.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MySpringbootQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringbootQuartzApplication.class, args);
    }

}

