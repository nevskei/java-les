package com.simbirsoft.java.resume;

import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("resume")
public class ResumeController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        ResumeService resumeService = new ResumeService();
        ResumeDto resume = resumeService.getResume();
        modelMap.put("fio", resume.getFio());
        modelMap.put("dob", resume.getDob());
        modelMap.put("avatar", resume.getAvatar());
        modelMap.put("education", resume.getEducation());
        modelMap.put("email", resume.getEmail());
        modelMap.put("experience", resume.getExperience());
        modelMap.put("phone", resume.getPhone());
        modelMap.put("skype", resume.getSkype());
        modelMap.put("target", resume.getTarget());
        return "resume/index";
    }
}
