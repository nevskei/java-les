/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simbirsoft.java.resume;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.*;
import com.simbirsoft.java.include.*;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ResumeServiceImpl implements ResumeService{
    
    private Boolean fromDb = true;
    private final String DIR_NAME = "src/main/java/com/simbirsoft/java/";
    private final String EXTENSION = "properties";
    
    @Autowired
    private ResumeRepository repository;
    
    public ResumeDto getResume() {
            Resume resume = repository.getOne(1L);
        if (resume == null) {
            ResumeDto resumeDto = new ResumeDto();
            File dir = new File(DIR_NAME);
            String[] filenames = dir.list(new ExtensionFilter(EXTENSION));
            List<Thread> threads = new ArrayList<>();
            for (int i = 0; i < filenames.length; i++) {
                Thread thread = new ReadFilesThread(resumeDto, DIR_NAME+filenames[i]);
                threads.add(thread);
                thread.start();
            }
            threads.forEach((Thread thread)->{
                try {
                    thread.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ResumeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            return resumeDto;
        }
        return resume;
    }
}
