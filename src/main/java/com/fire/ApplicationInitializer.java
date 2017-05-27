package com.fire;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInitializer implements CommandLineRunner {

    
    @Override
    public void run(String... arg0) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行完成<<<<<<<<<<<<<");
    }

}
