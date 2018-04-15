
package com.simbirsoft.java.resume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddBean {
    
    @Autowired
    private ResumeRepository repository;
    
    public void addResume() {
        Resume resume = new Resume("Иванов Иван", "10.09.80", "45682761056", "email@email.com", "skype", "https://pp.userapi.com/c621509/v621509021/1f2ef/iVlmN93YiA0.jpg", "target", "experience", "education", "addeducation");
        repository.save(resume);
    }
}
