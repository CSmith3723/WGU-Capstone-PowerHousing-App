package cs.capstone.powerhousing.services;

import cs.capstone.powerhousing.dao.UserRepository;
import cs.capstone.powerhousing.entities.UserInfo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/*
This class is how we are going to provide user details and check to see if the user exists in the database.
 */


@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

         UserInfo myUser = userRepository.findByUsername(username);

        if(myUser != null){
            return User.builder()
                    .username(myUser.getUsername())
                    .password(myUser.getUserPassword())
                    .roles(getRoles(myUser))
                    .build();
        }else{
            throw new UsernameNotFoundException("User not found.");
        }

    }

    private String[] getRoles(UserInfo user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }
}
