package cs.capstone.powerhousing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    String username;
    String role;

    public UserDTO(String username, String role) {
        this.username = username;
        this.role = role;
    }
}
