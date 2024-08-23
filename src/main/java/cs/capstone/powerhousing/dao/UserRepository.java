package cs.capstone.powerhousing.dao;

import cs.capstone.powerhousing.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, String> {

   UserInfo findByUsername(String username);


}
