error id: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Service/mmaService.java:org/springframework/web/client/RestClient#RequestHeadersSpec#retrieve().
file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Service/mmaService.java
empty definition using pc, found symbol in pc: org/springframework/web/client/RestClient#RequestHeadersSpec#retrieve().
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 934
uri: file:///C:/Users/david/techskill/demo/src/main/java/techskill/demo/Service/mmaService.java
text:
```scala
package techskill.demo.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import tools.jackson.databind.JsonNode;

@Service
public class mmaService{
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

    public JsonNode getNextEvent(int eventId){
        return mmaRestClient.get()
        .uri("https://api.sportsdata.io/v3/mma/Event/{eventId}", eventId)
        .retri@@eve()
        .body(JsonNode.class);
    }





}
```


#### Short summary: 

empty definition using pc, found symbol in pc: org/springframework/web/client/RestClient#RequestHeadersSpec#retrieve().