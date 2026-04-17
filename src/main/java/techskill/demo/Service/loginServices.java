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

         String email = login.getEmail();
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