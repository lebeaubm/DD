package com.example.dnd.model;

import com.example.dnd.model.Character;
import java.util.List;
import java.util.stream.Collectors;

public class Team {
    private String teamName;
    private List<Combatant> combatants;

    public Team(String teamName, List<Character> characters) {
        this.teamName = teamName;
        this.combatants = characters.stream()
                .map(Combatant::new)
                .collect(Collectors.toList());
    }

    public String getTeamName() {
        return teamName;
    }

    public List<Combatant> getCombatants() {
        return combatants;
    }

    public boolean hasAliveMembers() {
        return combatants.stream().anyMatch(Combatant::isAlive);
    }

    public boolean isEmpty() {
        return combatants.isEmpty();
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
