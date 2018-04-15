/*
 * Генерация резюме
 */
package com.simbirsoft.java;

import com.simbirsoft.java.resume.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;

@SpringBootApplication
public class App
{
    
    public static void main(String[] args) {
        
	ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        context.getBean(AddBean.class).addResume();
    }
    
}
