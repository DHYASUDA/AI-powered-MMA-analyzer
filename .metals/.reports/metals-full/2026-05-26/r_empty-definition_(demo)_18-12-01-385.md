error id: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Service/chatAiService.java:_empty_/NumberFormatExeption#
file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Service/chatAiService.java
empty definition using pc, found symbol in pc: _empty_/NumberFormatExeption#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1884
uri: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Service/chatAiService.java
text:
```scala
package techskill.demo.Service;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import tools.jackson.databind.JsonNode;
@Service
public class chatAiService{

    LocalDate curDate = LocalDate.now();
    //inject api and mma service
    private final mmaService mmaService;
    private final ChatClient chatClient;

    public chatAiService(mmaService mmaService, ChatClient.Builder builder){
        this.mmaService = mmaService;
        this.chatClient = builder
            .defaultSystem("You are an MMA assistant. Only answer MMA related questions.")
            .build();
    }

    public String chat(String userQuestion){
        JsonNode eventData = mmaService.getUpcomingEvents();
        //JsonNode nextEvent = mmaService.getNextEvent(900);

       //we can extract the name of the fighter in the prompt input
       String fighterName = chatClient.prompt()
       .system("Extract the fighter ID mentioned in the user input")
       .user(userQuestion)
       .call()
       .content()
       .trim();

       JsonNode nextEvent = null;
       try{
       String rawID = (chatClient.prompt()
       .system("Extract eventID from the upcoming events based on the current date (e.g if its 5/26/26, get the next event" + 
       eventData.toString()+ curDate + "return nothing if event doesnt exist" + "Only give me the number with no other text e.g(900")
       .user(userQuestion)
       .call()
       .content()
       .trim());

       int eventId = Integer.parseInt(rawID);
       nextEvent = mmaService.getNextEvent(eventId);
       }catch(NumberFormatException e){
        System.out.println("Event doesnt exist or didnt parse correctly");
       }


       JsonNode fighterStats = null;
       try {
           
       } catch (Number@@FormatExeption e) {
       }
        String systemPrompt = """
            You are an MMA assistant with access to real, up-to-date fight data.
            Use ONLY the event data below to answer questions — do not guess or
            use your training data for event details since it may be outdated.

           
           Rules:
           -When outputting all the fights, dont Rounds:x,y.
           -When asked about the upcoming event, give the upcoming event based on the current date, e.g if 
            its 5/26/26, give me next event on that date.



            Current MMA event data:
            """ + eventData.toString() + curDate.toString() + nextEvent.toString();
            return chatClient.prompt()
            .system(systemPrompt)
            .user(userQuestion)
            .call()
            .content();
    }


}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/NumberFormatExeption#