package com.example.dnd.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dnd.model.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByUserId(Long userId);
}
