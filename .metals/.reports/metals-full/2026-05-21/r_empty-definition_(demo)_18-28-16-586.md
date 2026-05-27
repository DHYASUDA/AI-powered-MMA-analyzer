error id: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/chatController.java:_empty_/CrossOrigin#origins#
file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/chatController.java
empty definition using pc, found symbol in pc: _empty_/CrossOrigin#origins#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 404
uri: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/chatController.java
text:
```scala
package techskill.demo.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origi@@ns = "http://localhost:5173") 
public class chatController{

        private final ChatClient chatClient;
        public chatController(ChatClient.Builder builder){
                this.chatClient = builder
                .defaultSystem("End every sentence with 'abc'")
                .build();
        }

        @GetMapping("/chat")
        public String chat(@RequestParam String message){
            return chatClient
            .prompt() //this starts building the prompt
            .user(message) // this is the message from the frontend react
            .call() // this sends the prompt to the ai
            .content(); //extracts the response from the ai
        }

}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/CrossOrigin#origins#