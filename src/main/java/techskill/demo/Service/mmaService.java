package techskill.demo.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public JsonNode getEventDetailsAN(int eventId){
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
    public List<String> getEventsByYear(String year) {
        List<String> eventList = new ArrayList<>();
        JsonNode events = mmaRestClient.get()
            .uri("https://api.sportsdata.io/v3/mma/scores/json/Schedule/UFC/" + year)
            .retrieve()
            .body(JsonNode.class);
    
        if (events != null) {
            for (JsonNode event : events) {
                String name = event.get("Name").asText();
                eventList.add(name);
            }
        }
        return eventList;
    }

   

    public List<Map<String, Object>> getAllFighterss() {
        List<Map<String, Object>> fighters = new ArrayList<>();
        
        JsonNode results = mmaRestClient.get()
            .uri("https://api.sportsdata.io/v3/mma/scores/json/FightersBasic")
            .retrieve()
            .body(JsonNode.class);
    
        for (JsonNode f : results) {
            try {
                String first    = !f.get("FirstName").isNull() ? f.get("FirstName").asText() : "";
                String last     = !f.get("LastName").isNull()  ? f.get("LastName").asText()  : "";
                String nickname = !f.get("Nickname").isNull()  ? f.get("Nickname").asText()  : "";
                String id       = !f.get("FighterId").isNull() ? f.get("FighterId").asText() : "";
    
                Map<String, Object> fighter = new HashMap<>();
                fighter.put("id",       id);
                fighter.put("name",     (first + " " + last).trim());
                fighter.put("nickname", nickname);
    
                // Add all stats fields
                fighter.put("WeightClass",              getTextOrNull(f, "WeightClass"));
                fighter.put("BirthDate",                getTextOrNull(f, "BirthDate"));
                fighter.put("Height",                   getDoubleOrNull(f, "Height"));
                fighter.put("Weight",                   getDoubleOrNull(f, "Weight"));
                fighter.put("Reach",                    getDoubleOrNull(f, "Reach"));
                fighter.put("Wins",                     getIntOrNull(f, "Wins"));
                fighter.put("Losses",                   getIntOrNull(f, "Losses"));
                fighter.put("Draws",                    getIntOrNull(f, "Draws"));
                fighter.put("NoContests",               getIntOrNull(f, "NoContests"));
                fighter.put("TechnicalKnockouts",       getIntOrNull(f, "TechnicalKnockouts"));
                fighter.put("TechnicalKnockoutLosses",  getIntOrNull(f, "TechnicalKnockoutLosses"));
                fighter.put("Submissions",              getIntOrNull(f, "Submissions"));
                fighter.put("SubmissionLosses",         getIntOrNull(f, "SubmissionLosses"));
                fighter.put("TitleWins",                getIntOrNull(f, "TitleWins"));
    
                fighters.add(fighter);
            } catch (Exception e) {
                System.out.println("Skipping fighter due to error: " + e.getMessage());
            }
        }
    
        return fighters;
    }
    
    // Helper methods — add these anywhere in the class
    private String getTextOrNull(JsonNode node, String field) {
        JsonNode val = node.get(field);
        return (val != null && !val.isNull()) ? val.asText() : null;
    }
    
    private Double getDoubleOrNull(JsonNode node, String field) {
        JsonNode val = node.get(field);
        return (val != null && !val.isNull()) ? val.asDouble() : null;
    }
    
    private Integer getIntOrNull(JsonNode node, String field) {
        JsonNode val = node.get(field);
        return (val != null && !val.isNull()) ? val.asInt() : null;
    }
    //basically we first get event search by event name, then we get the event ID, then we get all the fights from
    //event ID, then we get all the fightID, then we search fightID to display fight details
    public List<JsonNode> getUserEventDetailsByName(String eventName, String year) {
        System.out.println("getUserEventDetailsByName received: [" + eventName + "] year: " + year);
        int eventID = 0;
    
        JsonNode events = mmaRestClient.get()
            .uri("https://api.sportsdata.io/v3/mma/scores/json/Schedule/UFC/" + year)
            .retrieve()
            .body(JsonNode.class);
    
        if (events != null && events.isArray()) {
            for (JsonNode event : events) {
                String name = event.get("Name").asText().trim();
                if (name.equalsIgnoreCase(eventName.trim())) {
                    eventID = event.get("EventId").asInt();
                    System.out.println("Match found! EventID: " + eventID);
                    break;
                }
            }
        }
    
        if (eventID == 0) {
            System.out.println("No event matched: [" + eventName + "]");
            return new ArrayList<>();
        }
    
        JsonNode event = getEventDetailsAN(eventID);
    
        if (event == null) {
            System.out.println("getEventDetailsAN returned null for eventID: " + eventID);
            return new ArrayList<>();
        }
    
        JsonNode fights = event.get("Fights");
    
        if (fights == null || !fights.isArray()) {
            System.out.println("No fights found in event: " + eventID);
            return new ArrayList<>();
        }
    
        List<JsonNode> fightDetails = new ArrayList<>();
    
        for (JsonNode fight : fights) {
            int fightId = fight.get("FightId").asInt();
    
            try {
                JsonNode fightFinal = mmaRestClient.get()
                    .uri("https://api.sportsdata.io/v3/mma/stats/json/FightFinal/{fightId}", fightId)
                    .retrieve()
                    .body(JsonNode.class);
    
                if (fightFinal != null) {
                    fightDetails.add(fightFinal);
                    continue;
                }
            } catch (Exception e) {
                System.out.println("FightFinal not available for fightId " + fightId);
            }
    
            fightDetails.add(fight);
        }
    
        return fightDetails;
    }



    
}