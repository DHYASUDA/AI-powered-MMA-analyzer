error id: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/mmaController.java:
file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/mmaController.java
empty definition using pc, found symbol in pc: 
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 1216
uri: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Controller/mmaController.java
text:
```scala
package techskill.demo.Controller;

import java.util.List;

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
        @GetMapping("/get2026Events")
        public@@ List<String> events2026(){
            System.out.println(mmaService.getEventsFrom2026());
            return mmaService.getEventsFrom2026();
        }

}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 