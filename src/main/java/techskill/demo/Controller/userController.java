package techskill.demo.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import techskill.demo.DTO.nameDTO;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class userController {
    
    @PostMapping("/submit")
    public String printData(@RequestBody nameDTO request){
        System.out.println("hello " + request.getName());
        System.out.println(request);
        return "Hello" + request.getName();
    }


}
