error id: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/userController.java:java/lang/System#out.
file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/userController.java
empty definition using pc, found symbol in pc: java/lang/System#out.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1306
uri: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/userController.java
text:
```scala
package techskill.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import techskill.demo.DTO.nameDTO;
import techskill.demo.DTO.signUpDTO;
import techskill.demo.Service.signUpService;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class userController {

    private final signUpService userService;
    public userController(signUpService userService){
        this.userService = userService;
    }

    
    //login
    @PostMapping("/submit")
    public String printData(@RequestBody nameDTO request){
        System.out.println("hellov " + request.getName());
        System.out.println(request);
        return "Hello" + request.getName();
    }
    //signup
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody signUpDTO request){
        System.out.println("Received Signup Request:");
        System.out.println("Email: " + request.getEmail());
        System.ou@@t.println("Username: " + request.getUsername());
        System.out.println("Password: " + request.getPassword());

        try {
            // Call the service method you created earlier
            userService.registerUser(request);
            
            return ResponseEntity.ok("Signup successful!");
            
        } catch (Exception e) {
            // Return error message if email or username already exists
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: java/lang/System#out.