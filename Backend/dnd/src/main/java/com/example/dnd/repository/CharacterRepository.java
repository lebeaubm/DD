package com.example.dnd.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dnd.model.Character;
import com.example.dnd.model.User;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByUserId(Long userId);
    List<Character> findByUser(User user);
}
