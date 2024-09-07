package com.example.dnd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dnd.model.Character;
import com.example.dnd.model.User;
import com.example.dnd.repository.CharacterRepository;
import com.example.dnd.repository.UserRepository;



@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final UserRepository userRepository;

    public CharacterService(CharacterRepository characterRepository, UserRepository userRepository) {
        this.characterRepository = characterRepository;
        this.userRepository = userRepository;
    }

    // Method to save a character linked to a specific user
    public Character saveCharacter(String name, String race, String charClass, int level, String username) {
        // Fetch the user by username
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));

        // Create the character and set its properties
        Character character = new Character();
        character.setName(name);
        character.setRace(race);
        character.setCharClass(charClass);
        character.setLevel(level);
        character.setUser(user); // Link the character to the user

        // Save the character in the repository (and the database)
        return characterRepository.save(character);
    }

    // Method to get all characters for a given user
    public List<Character> getCharactersForUser(String username) {
        // Fetch the user by username
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));

        // Return all characters linked to that user
        return characterRepository.findByUserId(user.getId());
    }
}