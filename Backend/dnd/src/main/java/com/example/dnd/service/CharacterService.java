package com.example.dnd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dnd.model.Character;
import com.example.dnd.model.User;
import com.example.dnd.repository.CharacterRepository;
import com.example.dnd.repository.UserRepository;



@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private UserRepository userRepository;

    public Character createCharacter(String username, String name, String race, String className) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Character character = new Character();
        character.setName(name);
        character.setRace(race);
        character.setClassName(className);
        character.setUser(user);
        return characterRepository.save(character);
    }

    public List<Character> getCharactersForUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        return characterRepository.findByUserId(user.getId());
    }

    public Optional<Character> getCharacterById(Long id) {
        return characterRepository.findById(id);
    }

    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }
}