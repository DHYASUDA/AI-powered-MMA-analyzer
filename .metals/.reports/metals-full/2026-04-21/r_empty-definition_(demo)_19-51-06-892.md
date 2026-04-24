error id: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Service/loginServices.java:java/lang/String#
file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Service/loginServices.java
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol java/lang/String#
empty definition using fallback
non-local guesses:

offset: 747
uri: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Service/loginServices.java
text:
```scala
package techskill.demo.Service;

import org.springframework.stereotype.Service;

import techskill.demo.DTO.loginDTO;
import techskill.demo.Entity.userEntity;
import techskill.demo.Repositories.userRepo;


@Service
public class loginServices{

    //inject repo bean
    private userRepo userRepo;
    public loginServices(userRepo userRepo){
        this.userRepo = userRepo;
    }

    public userEntity getUser(loginDTO login) throws Exception{
        if(!userRepo.existsByEmail(login.getEmail())){
            System.out.println("user doesnt exist");
            throw new Exception("User doesnt exist");
        }   else {
            System.out.println(login.getEmail() + " user exists");
        }

         String@@ email = login.getEmail();
         String password = login.getPassword();
        
         userEntity user = userRepo.findByEmail(email)
         .orElseThrow(() -> new Exception("User not found"));
        System.out.println(user.toString());
         if(user.getPassword().equals(password)){
            System.out.println("Login succesful!");
         } else {
            System.out.println("Login unsucessful");
         }
        
        return user;
    }
    


}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 