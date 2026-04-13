error id: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/userController.java:java/io/PrintStream#println(+7).
file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/userController.java
empty definition using pc, found symbol in pc: java/io/PrintStream#println(+7).
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 699
uri: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/userController.java
text:
```scala
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
    
    //login
    @PostMapping("/submit")
    public String printData(@RequestBody nameDTO request){
        System.out.println("hellov " + request.getName());
        System.out.p@@rintln(request);
        return "Hello" + request.getName();
    }
    //signup
    @PostMapping("/signUp")
    public String signUpConfir

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: java/io/PrintStream#println(+7).