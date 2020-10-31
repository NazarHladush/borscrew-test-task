package com.botscrew.university;

import com.botscrew.university.view.MyView;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class UniversityApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(InitData initData, MyView myView) {
        return args -> {
            initData.init();
            myView.show();
        };
    }
}
