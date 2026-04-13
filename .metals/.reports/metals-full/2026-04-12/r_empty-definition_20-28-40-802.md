error id: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Repositories/userRepo.java:
file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Repositories/userRepo.java
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 409
uri: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Repositories/userRepo.java
text:
```scala
package techskill.demo.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import techskill.demo.Entity.userEntity;

import java.util.Optional;

@Repository
public interface userRepo extends JpaRepository<userEntity, Long>{
    Optional<userEntity> findByEmail(String email);
    Optional<userEntity> findByUsern(String@@ email);

    boolean existsByEmail(String email);
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 