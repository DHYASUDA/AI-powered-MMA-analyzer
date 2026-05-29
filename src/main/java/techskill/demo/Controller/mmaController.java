package techskill.demo.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import techskill.demo.Service.mmaService;
import tools.jackson.databind.JsonNode;
@RestController
@RequestMapping("/api/mma")
@CrossOrigin(origins = "http://localhost:5173")
public class mmaController{

        private final mmaService mmaService;
        public mmaController(mmaService mmaService){
            this.mmaService = mmaService;
        }

        @GetMapping("/upcomingEvents")
        public JsonNode upcomingEvents(){
            return mmaService.getUpcomingEvents();
        }
        @GetMapping("/nextEvent")
        public JsonNode nextEvent(int id){
            return mmaService.getEventDetails(id);
        }
        @GetMapping("/nextEventDetails")
        public JsonNode nextE(){
            System.out.println(mmaService.getNextEvent());
            return mmaService.getNextEvent();
        }

}