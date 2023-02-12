package com.psjw.kakaoshareprototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
        //(exclude = {DataSourceAutoConfiguration.class})
public class KakaoSharePrototypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(KakaoSharePrototypeApplication.class, args);
    }

}
