package com.simbirsoft.java.resume;

import com.simbirsoft.java.include.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.*;

@Service
public class ResumeServiceImpl implements ResumeService{
    
    private final String DIR_NAME = "src/main/java/com/simbirsoft/java/";
    private final String EXTENSION = "properties";
    
    @Override
    public ResumeDto getResume() {
        ResumeDto resume = new ResumeDto();
        
        File dir = new File(DIR_NAME);
        String[] filenames = dir.list(new ExtensionFilter(EXTENSION));
        List<Thread> threads = new ArrayList<>();
        for (String filename : filenames) {
            Thread thread = new ReadFilesThread(resume, DIR_NAME + filename);
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
        return resume;
    }
}
