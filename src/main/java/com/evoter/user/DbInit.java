package com.evoter.user;

import com.evoter.user.model.User;
import com.evoter.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.*;
import java.util.Base64;
import java.util.Optional;

@Component
public class DbInit {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void postConstruct() throws NoSuchAlgorithmException {
        Optional<User> userExist = userRepository.findByEmail("admin@evoter.com");

        if (userExist.isEmpty()) {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(512);
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            User user = new User();
            user.setName("Admin User");
            user.setAge(25);
            user.setEmail("admin@evoter.com");
//        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            user.setPassword("adminpass");
            user.setGender("Male");
            user.setNin("A-123456789");
            user.setLoggedIn(false);
            user.setRole(Role.ADMIN);
            user.setPrivateKey(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
            user.setPublicKey(Base64.getEncoder().encodeToString(privateKey.getEncoded()));

            userRepository.save(user);
        }
    }
}
