error id: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/chatController.java:local0
file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/chatController.java
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol local0
empty definition using fallback
non-local guesses:

offset: 850
uri: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/chatController.java
text:
```scala
package techskill.demo.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import techskill.demo.Service.chatAiService;

@RestController
@RequestMapping("/ai")
@CrossOrigin(origins = "http://localhost:5173") 
public class chatController{

        private final ChatClient chatClient;
        private final chatAiService chatService;
        public chatController(ChatClient.Builder builder, chatAiService chatService){
                this.chatService = chatService;
                this.chatClient = builder@@
                .defaultSystem("If fights are inactive, dont display.")
                .build();
        }

        @PostMapping("/chat")
        public String chat(@RequestBody String message){
            System.out.println(message);
            return chatService.chat(message);
        }


}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 