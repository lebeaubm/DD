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


    public Character saveCharacter(String name, String race, String charClass, int level, String username,
                               int strength, int dexterity, int constitution, int intelligence,
                               int wisdom, int charisma, int hitPoints, int armorClass, int initiative,
                               String background, String equipment) {
    // Find the user by username
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("User not found: " + username));

    // Create a new character and set its properties
    Character character = new Character();
    character.setName(name);
    character.setRace(race);
    character.setCharClass(charClass);
    character.setLevel(level);
    
    // Set the user object
    character.setUser(user);

    // Set the character sheet attributes
    character.setStrength(strength);
    character.setDexterity(dexterity);
    character.setConstitution(constitution);
    character.setIntelligence(intelligence);
    character.setWisdom(wisdom);
    character.setCharisma(charisma);
    character.setHitPoints(hitPoints);
    character.setArmorClass(armorClass);
    character.setInitiative(initiative);
    character.setBackground(background);
    character.setEquipment(equipment);

    // Save the character to the repository
    return characterRepository.save(character);
}





//     public Character saveCharacter(String name, String race, String charClass, int level, String username, 
//                                int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma, 
//                                int hitPoints, int armorClass, int initiative, String background, String equipment) {
//     User user = userRepository.findByUsername(username)
//         .orElseThrow(() -> new RuntimeException("User not found"));

//     Character character = new Character();
//     character.setName(name);
//     character.setRace(race);
//     character.setCharClass(charClass);
//     character.setLevel(level);
//     character.setUserId(user.getId());

//     // Set character sheet fields
//     character.setStrength(strength);
//     character.setDexterity(dexterity);
//     character.setConstitution(constitution);
//     character.setIntelligence(intelligence);
//     character.setWisdom(wisdom);
//     character.setCharisma(charisma);

//     character.setHitPoints(hitPoints);
//     character.setArmorClass(armorClass);
//     character.setInitiative(initiative);
//     character.setBackground(background);
//     character.setEquipment(equipment);

//     return characterRepository.save(character);
// }




    // Method to save a character linked to a specific user
    // public Character saveCharacter(String name, String race, String charClass, int level, String username) {
    //     // Fetch the user by username
    //     User user = userRepository.findByUsername(username)
    //         .orElseThrow(() -> new RuntimeException("User not found: " + username));

    //     // Create the character and set its properties
    //     Character character = new Character();
    //     character.setName(name);
    //     character.setRace(race);
    //     character.setCharClass(charClass);
    //     character.setLevel(level);
    //     character.setUser(user); // Link the character to the user

    //     // Save the character in the repository (and the database)
    //     return characterRepository.save(character);
    // }

    // Method to get all characters for a given user
    public List<Character> getCharactersForUser(String username) {
        // Fetch the user by username
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));

        // Return all characters linked to that user
        return characterRepository.findByUserId(user.getId());
    }
}