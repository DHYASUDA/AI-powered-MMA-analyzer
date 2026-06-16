package techskill.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import techskill.demo.Service.mmaService;
import tools.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api/mma")
@CrossOrigin(origins = "http://localhost:5173")
public class mmaController {

    private final mmaService mmaService;

    public mmaController(mmaService mmaService) {
        this.mmaService = mmaService;
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
}