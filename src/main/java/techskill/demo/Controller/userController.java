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
import techskill.demo.DTO.userEdit;
import techskill.demo.Entity.userEntity;
import techskill.demo.Service.loginServices;
import techskill.demo.Service.signUpService;
import techskill.demo.Service.userService;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class userController {

    private final signUpService userService;
    private final loginServices login;
    private final userService user;
    public userController(signUpService userService, loginServices login, userService user){
        this.userService = userService;
        this.login = login;
        this.user = user;
    }

    @PostMapping("/edit")
    public userEdit editt(@RequestBody userEdit request){
        System.out.println(request + "hello");
        System.out.println(request.getUserName());
        System.out.println("ID:" + request.getId());
        return request;

    }

    @PostMapping("/test")
    public String postMethodName(@RequestBody String entity) {
        System.out.println(entity + " hello");
        userService.test(entity);
        return entity;
    }

    @PostMapping("/biggie")
    public void aiDev(@RequestBody String entity) {
        System.out.println(entity);
        
    }
    //login
    @PostMapping("/submit")
    public String printData(@RequestBody nameDTO request){
        System.out.println("hellov " + request.getName());
        System.out.println("Hello " +request);
        return "Hello" + request.getName();
    }

    @PostMapping("/updateUserName")
    public void edit (@RequestBody userEdit request){
        System.out.println(request + "hello");
        System.out.println(request.getUserName());
        //user.updateUserName(request);
        try{
            System.out.println("dwjdwijdwqidjwidjwidj");
        user.haha(request);
        } catch(Exception e){
            System.out.println(e);
        }
       
    }

    @PostMapping("/login")
    public ResponseEntity<userEntity> login(@RequestBody loginDTO request){
        try{
            userEntity user =  login.getUser(request);
            return ResponseEntity.ok(user);
        }catch (Exception e) {
            // Return error message if email or username already exists
            return ResponseEntity.badRequest().build();
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
