package com.example.dnd.repository;

//import org.apache.el.stream.Optional;
import com.example.dnd.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //Optional<User> findByUsername(String username);
    //User findByEmail(String email);

    //Object findByUsername(String username);


    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
