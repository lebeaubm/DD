package com.example.dnd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Character {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SuppressWarnings("unused")
    private String name;
    @SuppressWarnings("unused")
    private String race;
    @SuppressWarnings("unused")
    private String className;
    @SuppressWarnings("unused")
    private int level;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setName(String name2) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setName'");
    }

    public void setRace(String race2) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setRace'");
    }

    public void setClassName(String className2) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setClassName'");
    }

    public void setUser(User user2) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setUser'");
    }

    // Getters and Setters

   

}
