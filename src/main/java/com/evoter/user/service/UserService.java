package com.evoter.user.service;

import com.evoter.user.Role;
import com.evoter.user.dto.UserDto;
import com.evoter.user.dto.UserLoginDto;
import com.evoter.user.model.User;
import com.evoter.user.repository.UserRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.*;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static java.time.LocalTime.now;

/**
 * @author showunmioludotun
 */
@Service
public class UserService {
    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(UserDto userDto) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(512);
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        User user = new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
//        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setPassword(userDto.getPassword());
        user.setGender(userDto.getGender());
        user.setNin(userDto.getNin());
        user.setLoggedIn(false);
        user.setRole(Role.USER);
        user.setPrivateKey(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        user.setPublicKey(Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> login(UserLoginDto userLoginDetails) {
        Optional<User> user = userRepository.findByEmail(userLoginDetails.getEmail());
        if (user.isPresent()) {
            if (user.get().getPassword().equals(userLoginDetails.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public User authenticateUser(User user) {
        user.setLoggedIn(true);
        return userRepository.save(user);
    }

    public void logout(User user) {
        user.setLoggedIn(false);
        userRepository.save(user);
    }
}
