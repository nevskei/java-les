/*
 * Генерация резюме
 */
package com.simbirsoft.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.simbirsoft.java.entity.*;

public class App
{
    public static void main( String[] args ) throws IOException
    {

        Properties properties = new Properties();
        Html html = new Html(properties);
        byte[] src = html.get().toString().getBytes();
        try {
            Files.write(Paths.get("src/main/java/com/simbirsoft/java/index.html"), src);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
