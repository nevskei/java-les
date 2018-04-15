package com.simbirsoft.java.resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestController {
    
    @Autowired
    private ResumeRepository repository;
    
    @RequestMapping(value="/rest", method = RequestMethod.POST)
    @ResponseBody
    public Resume addResume(@RequestBody Resume resume) {
        repository.save(resume);
        return resume;
    }
    
    @RequestMapping(value= "rest/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Resume getMyData(@PathVariable String id) {
        Resume resume = repository.getOne(new Long(id));
        return resume;
    }
    
    @RequestMapping(value="/rest/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Resume updateResume(@PathVariable String id, @RequestBody Resume resume) {
        resume.setId(new Long(id));
        repository.save(resume);
        return resume;
    }
    
    @RequestMapping(value="/rest/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Resume deleteResume(@PathVariable String id) {
        Resume resume = repository.getOne(new Long(id));
        repository.delete(resume);
        return resume;
    }
    
}
