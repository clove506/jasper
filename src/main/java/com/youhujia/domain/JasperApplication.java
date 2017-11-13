package com.youhujia.domain;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by lilac on 13/11/2017.
 */
@SpringBootApplication
public class JasperApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(JasperApplication.class).web(true).run(args);
    }

}
