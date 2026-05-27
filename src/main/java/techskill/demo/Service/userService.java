package techskill.demo.Service;

import org.springframework.stereotype.Service;

import techskill.demo.DTO.userEdit;
import techskill.demo.Entity.userEntity;
import techskill.demo.Repositories.userRepo;
@Service
public class userService{

    private userRepo userRepo;
    public userService(userRepo userRepo){
        this.userRepo = userRepo;
    }

    public String updateUserName(userEdit request){
        System.out.println("Testing " + request.getUserName());
        return request.getUserName();
    }
    public userEntity haha(userEdit request) throws Exception{
        userEntity user = userRepo.findById(request.getId()).orElseThrow(() -> new Exception("User not found"));
        String newUserName = request.getUserName();
        System.out.println("Im here");
        user.setUserName(newUserName);

        return userRepo.save(user);
    }

    public String printName(String request){
        
        System.out.println(request);
        return request;
    }

} 