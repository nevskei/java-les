/*
 * Генерация резюме
 */
package com.simbirsoft.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simbirsoft.java.entity.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class App
{
    //private final Html html;
    
    public static void main(String[] args) {
        
	SpringApplication.run(App.class, args);
        /*
        @SuppressWarnings("resource") 
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App  app = (App) ctx.getBean("app");
        app.genHtml();
*/
    }
  /*  
    public App(Html html){
        super();
        this.html = html;
    }
    private void genHtml() {
        byte[] src = html.get().toString().getBytes();
        try {
            Files.write(Paths.get("src/main/java/com/simbirsoft/java/index.html"), src);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**/
}
