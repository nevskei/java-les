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
import com.simbirsoft.java.entity.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    private final Html html;
    
    public static void main( String[] args ) throws IOException
    {
        @SuppressWarnings("resource") 
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App  app = (App) ctx.getBean("app");
        app.genHtml();
    }
    
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
}
