package techskill.demo.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import techskill.demo.DTO.loginDTO;
import techskill.demo.DTO.fightCard;
import tools.jackson.databind.JsonNode;

@Service
public class chatAiService{

    LocalDate curDate = LocalDate.now();
    //inject api and mma service
    private final mmaService mmaService;
    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    public chatAiService(mmaService mmaService, ChatClient.Builder builder, VectorStore vectorStore){
        this.mmaService = mmaService;
        this.vectorStore = vectorStore;
        this.chatClient = builder
            .defaultSystem("You are an MMA assistant. Only answer MMA related questions.")
            .build();
    }

    public fightCard getNextEventCard() {
        JsonNode event = mmaService.getNextEvent();
        if (event == null) {
            return null;   // no scheduled event found
        }
    
        String eventData = event.toString();   // the JSON as a string
    
        return chatClient.prompt()
            .system("You are an MMA assistant. Use ONLY the provided event data. Do not invent fights.")
            .user("Event data (JSON):\n" + eventData
                + "\n\nExtract the event name, the date, and every fight as fighterA vs fighterB.")
            .call()
            .entity(fightCard.class);
    }

    public String chat(String userQuestion){
        JsonNode eventData = mmaService.getUpcomingEvents();
        //JsonNode nextEvent = mmaService.getNextEvent(900);
        List<String> fighters = new ArrayList<>();
       //we can extract the name of the fighter in the prompt input
       String fighterNamesRaw = chatClient.prompt()
       .system("""
                Extract ALL fighter names mentioned in the user input.
                Output ONLY the names separated by a comma and nothing else.
                Examples:
                - "what is jon jones record" → "jon jones"
                - "who wins israel or joe pyfer" → "israel adesanya,joe pyfer"
                - "compare adesanya and whittaker" → "israel adesanya,robert whittaker"
                Do not include punctuation other than the comma separator.
            """)
       .user(userQuestion)
       .call()
       .content()
       .trim(); 
    

       String[] fighterNames = fighterNamesRaw.split(",");

       StringBuilder fighterDataBuilder = new StringBuilder();

       for (String name : fighterNames) {
           String trimmedName = name.trim();
           if (trimmedName.isEmpty()) continue;

           JsonNode record = mmaService.getFighterByName(trimmedName);
           JsonNode stats  = mmaService.getStatsByName(trimmedName);

           fighterDataBuilder
               .append("\n--- Fighter: ").append(trimmedName).append(" ---\n")
               .append("Record: ").append(record != null ? record.toString() : "Not found").append("\n")
               .append("Stats: ").append(stats  != null ? stats.toString()  : "Not found").append("\n");
       }
       

    
       JsonNode nextEventDetails = mmaService.getNextEvent();
       JsonNode fightOdds = mmaService.getFightOdds();
      

        String systemPrompt = """
            You are an MMA assistant with access to real, up-to-date fight data.
            Use ONLY the event data below to answer questions — do not guess or
            use your training data for event details since it may be outdated.

           
           Rules:
           
           -When outputing all the fights, dont display Rounds:x,y.
           -When asked about the upcoming event, give the upcoming event based on the current date, e.g if 
            its 5/26/26, give me next event on that date.
           -If user request fighter data, neatly the stats
           - When user asks for the next event by details, e.g 'give me all the fights for the next fight', print
            all the fights in that card;
            - When user asks who you think wins, x or y? get fightert stats for both fighters, and compare both fighters
            fighting style, figting stats (x fighter lands more signficiant shots, but y lands more takedowns.)
            - remove any "**" asteriks in the chat

            -Format your answer in Markdown. Start with one short sentence naming the event and date, then list the fights as a numbered list with each fighter's name in bold
            - Format your answer in Markdown. Start with the fighters fighting in bold, break the line, then markdown the next fight

            Current MMA event data:
            """ + eventData.toString() + curDate.toString()  + fighterDataBuilder.toString() + nextEventDetails + fightOdds;

            return chatClient.prompt()
            .system(systemPrompt)
            .user(userQuestion)
            .advisors(QuestionAnswerAdvisor.builder(vectorStore).build())//advisors is what retireves RAG data
            .call()
            .content();
    }


}