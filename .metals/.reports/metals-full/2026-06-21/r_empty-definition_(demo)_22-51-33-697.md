error id: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/mmaController.java:_empty_/chatAiService#getNextEventCard#
file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/mmaController.java
empty definition using pc, found symbol in pc: _empty_/chatAiService#getNextEventCard#
semanticdb not found
empty definition using fallback
non-local guesses:

offset: 2256
uri: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/mmaController.java
text:
```scala
package techskill.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import techskill.demo.Service.mmaService;
import techskill.demo.Service.chatAiService;
import tools.jackson.databind.JsonNode;

import techskill.demo.DTO.fightCard;

@RestController
@RequestMapping("/api/mma")
@CrossOrigin(origins = "http://localhost:5173")
public class mmaController {

    private final mmaService mmaService;
    private final chatAiService chatService;
    public mmaController(mmaService mmaService, chatAiService chatService) {
        this.mmaService = mmaService;
        this.chatService = chatService;
    }

    @GetMapping("/upcomingEvents")
    public JsonNode upcomingEvents() {
        return mmaService.getUpcomingEvents();
    }

    @GetMapping("/nextEvent")
    public JsonNode nextEvent(int id) {
        return mmaService.getEventDetails(id);
    }

    @GetMapping("/nextEventDetails")
    public JsonNode nextE() {
        System.out.println(mmaService.getNextEvent());
        return mmaService.getNextEvent();
    }

    @GetMapping("/get2026Events")
    public List<String> getEvents(@RequestParam(defaultValue = "2026") String year) {
        return mmaService.getEventsByYear(year);
    }

    @GetMapping("/getFightDetails")
    public List<JsonNode> fightDetails(
            @RequestParam String eventName,
            @RequestParam(defaultValue = "2026") String year) {
        return mmaService.getUserEventDetailsByName(eventName, year);
    }

    @GetMapping("/getAllFighters")
    public List<Map<String, Object>> getAllFighters() {
        return mmaService.getAllFighterss();
    }

    @GetMapping("/getFighterStats")
    public JsonNode getFighterStats(@RequestParam int fighterId) {
        return mmaService.getFighterStats(fighterId);
    }
    @GetMapping("/ai/next-event")
    public fightCard nextEvent(){
        return chatService.ge@@tNextEventCard();
    }
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/chatAiService#getNextEventCard#