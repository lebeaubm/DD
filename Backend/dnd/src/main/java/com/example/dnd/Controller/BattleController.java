package com.example.dnd.Controller;

import com.example.dnd.model.Character;
import com.example.dnd.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/battle")
public class BattleController {

    @Autowired
    private BattleService battleService;

    @PostMapping("/start")
    public ResponseEntity<String> startBattle(@RequestParam Long characterId1, @RequestParam Long characterId2) {
        String battleResult = battleService.startBattle(characterId1, characterId2);
        return ResponseEntity.ok(battleResult);
    }


}
