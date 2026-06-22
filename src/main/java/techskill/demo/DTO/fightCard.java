package techskill.demo.DTO;

import java.util.List;


public record fightCard(
    String eventName,
    String date,
    List<Fight> fights
) {
    public record Fight(String fighterA, String fighterB) {}
}