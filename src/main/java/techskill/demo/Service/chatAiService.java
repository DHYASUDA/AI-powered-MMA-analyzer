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
       .system("Extract the fighter name mentioned in the user input, only output the name and nothing else e.g what is jon jones record, this must output only 'jon jones'")
       .user(userQuestion)
       .call()
       .content()
       .trim(); 

       System.out.println(fighterName);
       JsonNode fighterStat = mmaService.getFighterByName(fighterName);
      
/* 
       JsonNode fighterStats = null;
       try {
           String rawFighterID = chatClient.prompt()
           .system("Extract fighter ID from the upcoming events" + nextEvent + "Only return the number, no text e.g(140000628)")
           .user(userQuestion)
           .call()
           .content()
           .trim();
           int fighterID = Integer.parseInt(rawFighterID);
           fighterStats = mmaService.getFighterStats(fighterID);
       } catch (NumberFormatException e) {
        System.out.println("Fighter ID doesnt exist or didnt parse correctly");
       }
        */


        String systemPrompt = """
            You are an MMA assistant with access to real, up-to-date fight data.
            Use ONLY the event data below to answer questions — do not guess or
            use your training data for event details since it may be outdated.

           
           Rules:
           -When outputting all the fights, dont Rounds:x,y.
           -When asked about the upcoming event, give the upcoming event based on the current date, e.g if 
            its 5/26/26, give me next event on that date.
           -If user request fighter data, neatly the stats


            Current MMA event data:
            """ + eventData.toString() + curDate.toString()  + fighterStat;

            return chatClient.prompt()
            .system(systemPrompt)
            .user(userQuestion)
            .call()
            .content();
    }


}