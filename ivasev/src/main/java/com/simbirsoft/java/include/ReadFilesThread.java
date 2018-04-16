package com.simbirsoft.java.include;

import com.simbirsoft.java.resume.ResumeDto;
import com.simbirsoft.java.resume.ResumeServiceImpl;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadFilesThread  extends Thread {
        private ResumeDto resume;
        private final String path;

        public ReadFilesThread(ResumeDto resume, String path) {
            this.resume = resume;
            this.path = path;
        }

        public void run() {
            java.util.Properties property = new java.util.Properties();
            try {
                property.load(new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-8")));
                Method[] methods = resume.getClass().getMethods();
                
                property.forEach((key, prop)->{
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("set");
                    char[] c = key.toString().toLowerCase().toCharArray();
                    c[0] = Character.toUpperCase(c[0]);
                    stringBuilder.append(c);
                    for (Method m : methods) {
                      if (m.getName().equals(stringBuilder.toString())) {
                          try {
                              m.invoke(resume, prop.toString());
                          } catch (IllegalAccessException ex) {
                              Logger.getLogger(ResumeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                          } catch (IllegalArgumentException ex) {
                              Logger.getLogger(ResumeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                          } catch (InvocationTargetException ex) {
                              Logger.getLogger(ResumeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                          }
                        break;
                      }
                    }
                });
            } catch (FileNotFoundException e) {
                System.out.println("Не найден файл настроек");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }   
}
