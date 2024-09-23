package com.example.dnd.dto;

import com.example.dnd.model.Character;
import java.util.List;




public class TeamBattleRequest {
    private List<Character> team1;
    private List<Character> team2;

    public List<Character> getTeam1() {
        return team1;
    }

    public void setTeam1(List<Character> team1) {
        this.team1 = team1;
    }

    public List<Character> getTeam2() {
        return team2;
    }

    public void setTeam2(List<Character> team2) {
        this.team2 = team2;
    }

    public boolean isValid() {
        return team1 != null && !team1.isEmpty() && team2 != null && !team2.isEmpty();
    }

    public String getValidationError() {
        if (team1 == null || team1.isEmpty()) {
            return "Team 1 must contain at least one character";
        }
        if (team2 == null || team2.isEmpty()) {
            return "Team 2 must contain at least one character";
        }
        return null;
    }









}
