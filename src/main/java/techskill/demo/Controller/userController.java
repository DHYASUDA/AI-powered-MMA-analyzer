package techskill.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import techskill.demo.DTO.loginDTO;
import techskill.demo.DTO.nameDTO;
import techskill.demo.DTO.signUpDTO;
import techskill.demo.Service.loginServices;
import techskill.demo.Service.signUpService;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class userController {

    private final signUpService userService;
    private final loginServices login;
    public userController(signUpService userService, loginServices login){
        this.userService = userService;
        this.login = login;
    }


    @PostMapping("/test")
    public String postMethodName(@RequestBody String entity) {
        
        System.out.println(entity + " hello");
        userService.test(entity);
        return entity;
    }

    
    
    //login
    @PostMapping("/submit")
    public String printData(@RequestBody nameDTO request){
        System.out.println("hellov " + request.getName());
        System.out.println(request);
        return "Hello" + request.getName();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody loginDTO request){
        try{
            login.getUser(request);
            return ResponseEntity.ok("dw");
        }catch (Exception e) {
            // Return error message if email or username already exists
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }

    //signup
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody signUpDTO request){
        System.out.println("Received Signup Request:");
        System.out.println("Email: " + request.getEmail());
        System.out.println("Username: " + request.getUsername());
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
