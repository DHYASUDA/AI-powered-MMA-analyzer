error id: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Service/mmaService.java:_empty_/fightDetails#
file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Service/mmaService.java
empty definition using pc, found symbol in pc: _empty_/fightDetails#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 5390
uri: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Service/mmaService.java
text:
```scala
package techskill.demo.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import tools.jackson.databind.JsonNode;

@Service
public class mmaService{
    private int eventId;
    //we inject bean for http request to mma api servers
    private final RestClient mmaRestClient;
    public mmaService(RestClient mmaRestClient){
        this.mmaRestClient = mmaRestClient;
    }
    

    //we get the upcoming event schedule for ufc
    public JsonNode getUpcomingEvents(){
        return mmaRestClient.get()//retrieves uri
        .uri("https://api.sportsdata.io/v3/mma/scores/json/Schedule/UFC/2026")//api link
        .retrieve()//sends request
        .body(JsonNode.class); //return data as a json file
    }

    public JsonNode getEventDetails(int eventId){
        return mmaRestClient.get()
        .uri("https://api.sportsdata.io/v3/mma/scores/json/Event/{eventId}", eventId)
        .retrieve()
        .body(JsonNode.class);
    }

    public JsonNode getFighterStats(int fighterId){
        return mmaRestClient.get()
        .uri("https://api.sportsdata.io/v3/mma/scores/json/Fighter/{fighterId}", fighterId)
        .retrieve()
        .body(JsonNode.class);
    }
    public JsonNode getFighterByName(String name){
        JsonNode allFighters = mmaRestClient.get()
        .uri("https://api.sportsdata.io/v3/mma/scores/json/FightersBasic")
        .retrieve()
        .body(JsonNode.class);
        if(allFighters != null && allFighters.isArray()){
            for(JsonNode fighter : allFighters){
                String firstName = fighter.get("FirstName").asString();
                String lastName = fighter.get("LastName").asString();
                String fullName = (firstName + " " + lastName).toLowerCase();
                if(fullName.contains(name.toLowerCase())){
                    return fighter;
                }
            }
        }
        System.out.println("Did not find any fighter.");
        return null;
    }
    public JsonNode getNextEvent(){
        String test = "";
        JsonNode events = getUpcomingEvents();
        if (events != null && events.isArray()) {
            for(JsonNode event : events){
                String status = event.get("Status").asText();
                
                if(status.equals("Scheduled")){
                   //eventID = events.get("EventId").asInt();
                    eventId = event.get("EventId").asInt();
                    return getEventDetails(eventId);
                }
            }
            
        }
        System.out.println("Events not loaded or no event is active.");
        return null;
    }
    //basically get event details by date or by main card event
   // public JSonNode getEventDetailsByDetails(){
        
   // }
    
    
    public JsonNode getStatsByName(String name){
        JsonNode allFighters = mmaRestClient.get()
        .uri("https://api.sportsdata.io/v3/mma/scores/json/FightersBasic")
        .retrieve()
        .body(JsonNode.class);
        if(allFighters != null && allFighters.isArray()){
            for(JsonNode fighter : allFighters){
                String firstName = fighter.get("FirstName").asString();
                String lastName = fighter.get("LastName").asString();
                String fullName = (firstName + " " + lastName).toLowerCase();
                if(fullName.contains(name.toLowerCase())){
                    int fighterID = fighter.get("FighterId").asInt();
                    return getFighterStats(fighterID); 
                }
            }
        }
        return null;
    }


    public JsonNode getAllFighters(){
        return mmaRestClient.get()
        .uri("https://api.sportsdata.io/v3/mma/scores/json/FightersBasic")
        .retrieve()
        .body(JsonNode.class);
    }

    public JsonNode getFightOdds(){
        JsonNode odds = mmaRestClient.get()
        .uri("https://api.sportsdata.io/v3/mma/odds/json/EventOdds/{eventId}", eventId)
        .retrieve()
        .body(JsonNode.class);
        System.out.println(eventId);
        return odds;
    }
    public List<String> getEventsFrom2026(){
        List<String> event2026 = new ArrayList<>();
        JsonNode events = mmaRestClient.get()
        .uri("https://api.sportsdata.io/v3/mma/scores/json/Schedule/UFC/2026")
        .retrieve()
        .body(JsonNode.class);

        for(JsonNode eventName : events){
            String eventNames = eventName.get("Name").toString();
            event2026.add(eventNames);
        }
        return event2026;
    }
    public JsonNode getUserEventDetailsByName (String eventName){
        int eventID = 0;
        JsonNode events = mmaRestClient.get()
        .uri("https://api.sportsdata.io/v3/mma/scores/json/Schedule/UFC/2026")
        .retrieve()
        .body(JsonNode.class);

        for(JsonNode event : events){
            String eventNames = event.get("Name").toString();
            if(eventNames.equals(eventName)){
                eventID = event.get("EventId").asInt();
            }
        }
        JsonNode event = getEventDetails(eventID);
        if (event == null || !event.isArray()) {
            return fightDetail@@s;
        }
        for (JsonNode detailedEvent : event) {
            fightIDs.add(detailedEvent.get("FightId").asInt());
        }
        for (int fightId : fightIDs) {
            JsonNode fightDetail = mmaRestClient.get()
                    .uri("https://api.sportsdata.io/v3/mma/stats/json/FightFinal/{fightId}", fightId)
                    .retrieve()
                    .body(JsonNode.class);
            fightDetails.add(fightDetail);
        }
        return fightDetails;
    }


    
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/fightDetails#