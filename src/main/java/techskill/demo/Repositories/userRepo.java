package techskill.demo.Repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import techskill.demo.Entity.userEntity;

@Repository
public interface userRepo extends JpaRepository<userEntity, Long>{
    Optional<userEntity> findByEmail(String email);
    Optional<userEntity> findByUserName(String userName);
    Optional<userEntity> findById(Long id);
    
    
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    boolean existsPasswordByEmail(String email, String password);
    boolean existsPasswordByUserName(String userName, String password);
    
}
