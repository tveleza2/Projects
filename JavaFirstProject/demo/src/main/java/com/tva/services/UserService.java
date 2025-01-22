package com.tva.services;
import java.time.LocalDate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.tva.models.*;

public class UserService {
    protected BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public UserService() {
    }
    public User createUser(String id, String userName, String password, boolean isAdmin, LocalDate creationDate) {
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setPassword(encoder.encode(password));
        user.setAdmin(isAdmin);
        user.setCreationDate(creationDate);
        return user;
    }
}
