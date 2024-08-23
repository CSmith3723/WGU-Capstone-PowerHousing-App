package cs.capstone.powerhousing.services;


import cs.capstone.powerhousing.dao.UserRepository;
import cs.capstone.powerhousing.dto.UserDTO;
import cs.capstone.powerhousing.entities.UserInfo;
import cs.capstone.powerhousing.user.WebUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService {

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> findAllUsers() {
        List<UserInfo> userList = userRepository.findAll();

        return userList.stream().map(user -> new UserDTO(user.getUsername(), user.getRole()))
                .collect(Collectors.toList());
    }


    public void register(WebUser webUser){
        System.out.println("Saving user to database.");
        UserInfo userInfo = new UserInfo();

        userInfo.setUsername(webUser.getUsername());
        userInfo.setUserPassword(passwordEncoder.encode(webUser.getPassword()));
        userInfo.setRole("USER");
        userRepository.save(userInfo);
    }


    public void save(UserInfo userInfo) {
        userInfo.setUsername(userInfo.getUsername());
        userInfo.setUserPassword(userInfo.getUserPassword());
        userInfo.setRole(userInfo.getRole());
        userRepository.save(userInfo);
    }


    public UserInfo findByUsername(String username) {
        return userRepository.findByUsername(username);

    }

    public void deleteUser(String username){
        userRepository.deleteById(username);
    }


}
