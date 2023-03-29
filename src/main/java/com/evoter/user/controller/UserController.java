package com.evoter.user.controller;

import com.evoter.user.dto.UserDto;
import com.evoter.user.dto.UserLoginDto;
import com.evoter.user.model.User;
import com.evoter.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author showunmioludotun
 */
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/save")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserDto userDto, BindingResult result) {
         try {
             Optional<User> existingUser = userService.findByEmail(userDto.getEmail());

             if (existingUser.isPresent()) {
                 return new ResponseEntity<>(HttpStatus.CONFLICT);
             }

             if (result.hasErrors()) {
                 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
             }

             User savedUser = userService.addUser(userDto);

             if (savedUser == null) {
                 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
             }

             return new ResponseEntity<>("Success", HttpStatus.CREATED);
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long id) {
        try {
            User user = userService.getUserById(id);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute(value = "userLoginDetails") UserLoginDto userLoginDetails) {
        try {
            Optional<User> user = userService.login(userLoginDetails);
            if (user == null) {
                return "redirect:/login?failure";
            }
            User authUser = userService.authenticateUser(user.get());
            return "redirect:/dashboard/"+ authUser.getId();
        }catch (Exception e) {
            return "redirect:/login?failure";
        }
    }

    @PostMapping("/logout")
    public String logout(@RequestParam("userId") Long userId) {
        try {
            User user = userService.getUserById(userId);
            userService.logout(user);
            return "redirect:/login";
        }catch (Exception e) {
            return "redirect:/login";
        }
    }

//    @PutMapping("/users/{userId}")
//    public ResponseEntity<User> updateUser(@PathVariable("userId") Long id, @RequestBody UpdateUserRequest request) {
//        try {
//            User user = userService.updateUser(id, request);
//            if (user == null) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
