package techskill.demo.Repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import techskill.demo.Entity.userEntity;

@Repository
public interface userRepo extends JpaRepository<userEntity, Long>{
    Optional<userEntity> findByEmail(String email);
    Optional<userEntity> findByUsername(String username);

    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
}
