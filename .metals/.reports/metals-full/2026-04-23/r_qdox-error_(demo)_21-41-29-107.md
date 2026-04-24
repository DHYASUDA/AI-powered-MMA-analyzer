error id: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/userController.java
file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/userController.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[24,27]

error in qdox parser
file content:
```java
offset: 919
uri: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/userController.java
text:
```scala
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
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class userController {

    private final signUpService userService;
    private final loginServices login;
    private final useruser;@@
    public userController(signUpService userService, loginServices login, userEdit user){
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

    
    
    //login
    @PostMapping("/submit")
    public String printData(@RequestBody nameDTO request){
        System.out.println("hellov " + request.getName());
        System.out.println("Hello " +request);
        return "Hello" + request.getName();
    }

    @PostMapping("/updateUserName")
    public ResponseEntity<userEntity> edit (@RequestBody userEdit request){
        System.out.println(request + "hello");
        System.out.println(request.getUserName());
        System.out.println();
        user.u
        
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

```

```



#### Error stacktrace:

```
com.thoughtworks.qdox.parser.impl.Parser.yyerror(Parser.java:2025)
	com.thoughtworks.qdox.parser.impl.Parser.yyparse(Parser.java:2147)
	com.thoughtworks.qdox.parser.impl.Parser.parse(Parser.java:2006)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:232)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:190)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:94)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:89)
	com.thoughtworks.qdox.library.SortedClassLibraryBuilder.addSource(SortedClassLibraryBuilder.java:162)
	com.thoughtworks.qdox.JavaProjectBuilder.addSource(JavaProjectBuilder.java:174)
	scala.meta.internal.mtags.JavaMtags.indexRoot(JavaMtags.scala:49)
	scala.meta.internal.metals.SemanticdbDefinition$.foreachWithReturnMtags(SemanticdbDefinition.scala:99)
	scala.meta.internal.metals.Indexer.indexSourceFile(Indexer.scala:560)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3(Indexer.scala:691)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3$adapted(Indexer.scala:688)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:630)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:628)
	scala.collection.AbstractIterator.foreach(Iterator.scala:1313)
	scala.meta.internal.metals.Indexer.reindexWorkspaceSources(Indexer.scala:688)
	scala.meta.internal.metals.MetalsLspService.$anonfun$onChange$2(MetalsLspService.scala:940)
	scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
	scala.concurrent.Future$.$anonfun$apply$1(Future.scala:691)
	scala.concurrent.impl.Promise$Transformation.run(Promise.scala:500)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
	java.base/java.lang.Thread.run(Thread.java:1583)
```
#### Short summary: 

QDox parse error in file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/userController.java