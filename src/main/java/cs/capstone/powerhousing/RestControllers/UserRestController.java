package cs.capstone.powerhousing.RestControllers;

import cs.capstone.powerhousing.dto.UserDTO;
import cs.capstone.powerhousing.entities.UserInfo;
import cs.capstone.powerhousing.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    UserService userService;

    PasswordEncoder encoder;

    public UserRestController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.encoder = passwordEncoder;
    }

    @PostMapping("/newUser")
    public UserDTO register(@RequestBody UserInfo userInfo) {
        userInfo.setUserPassword(encoder.encode(userInfo.getUserPassword()));
        userService.save(userInfo);
        return new UserDTO(userInfo.getUsername(), userInfo.getRole());
    }

    @PutMapping("/{username}")
    public UserDTO updateUser(@RequestBody UserInfo userInfo, @PathVariable String username) {
        UserInfo updateUser = userService.findByUsername(username);
        if (updateUser.getUsername().equals(userInfo.getUsername())) {
            updateUser.setUsername(userInfo.getUsername());
            updateUser.setRole(userInfo.getRole());
            updateUser.setUserPassword(encoder.encode(userInfo.getUserPassword()));
            userService.save(updateUser);
        }
        return new UserDTO(userInfo.getUsername(), userInfo.getRole());
    }

    @GetMapping()
    public List<UserDTO> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{username}")
    public UserDTO findByUsername(@PathVariable String username) {
        UserInfo user = userService.findByUsername(username);
        if (user != null) {
            return new UserDTO(user.getUsername(), user.getRole());
        } else {
            return null;
        }
    }


    @GetMapping("/currentUser")
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName(); // Return the username
        }
        return "Anonymous";
    }

    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
    }


}
