package techskill.demo.Service;

import org.springframework.stereotype.Service;

import techskill.demo.DTO.signUpDTO;
import techskill.demo.Entity.userEntity;
import techskill.demo.Repositories.userRepo;


@Service
public class signUpService {
    private final userRepo userRepo;
    
    public signUpService(userRepo userRepo){
        this.userRepo = userRepo;
    }

    public userEntity registerUser(signUpDTO signUp) throws Exception{
        if(userRepo.existsByEmail(signUp.getEmail())){
            throw new Exception("Email already in use");
        }
        if(userRepo.existsByUserName(signUp.getUsername())){
            throw new Exception("Username exists");
        }
        userEntity user = new userEntity();
        user.setEmail(signUp.getEmail());
        user.setUserName(signUp.getUsername());
        user.setPassword(signUp.getPassword());
        
        return userRepo.save(user);
    }


}
