package techskill.demo.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class nameDTO {
    /** Accepts JSON keys "name" or "userName" (frontend often sends userName). */
    @JsonProperty("name")
    @JsonAlias("userName")
    private String name;
    private int age;

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
}
