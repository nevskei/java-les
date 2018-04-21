/*
 * Генерация резюме
 */
package com.simbirsoft.java;

import com.simbirsoft.java.service.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App
{    
    public static void main( String[] args ) throws IOException
    {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        Html html = context.getBean(Html.class);
        byte[] src = html.get().toString().getBytes();
        try {
            Files.write(Paths.get("src/main/java/com/simbirsoft/java/index.html"), src);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
