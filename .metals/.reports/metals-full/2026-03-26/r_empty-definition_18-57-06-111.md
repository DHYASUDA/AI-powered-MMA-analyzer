error id: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Entity/userEntity.java:_empty_/Column#
file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Entity/userEntity.java
empty definition using pc, found symbol in pc: _empty_/Column#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 411
uri: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Entity/userEntity.java
text:
```scala
package techskill.demo.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "KalshiUsers")
public class userEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @@@Column(name = "userName", nullable = false, length = 100)
    private String userName;
    private String email;
    private String password;
    
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/Column#